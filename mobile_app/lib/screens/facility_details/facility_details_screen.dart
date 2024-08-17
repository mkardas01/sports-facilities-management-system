import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:sport_plus/models/sport_facility.dart';
import 'package:sport_plus/screens/facility_details/bloc/facility_details_bloc.dart';
import 'package:sport_plus/screens/facility_details/facility_details_loaded_view.dart';
import 'package:sport_plus/widgets/error_view.dart';
import 'package:sport_plus/widgets/loading_view.dart';

class FacilityDetailsScreen extends StatefulWidget {
  const FacilityDetailsScreen({super.key});
  static const String route = "/facility-details";

  @override
  State<FacilityDetailsScreen> createState() => _FacilityDetailsScreenState();
}

class _FacilityDetailsScreenState extends State<FacilityDetailsScreen> {
  @override
  void didChangeDependencies() {
    var modalRoute = ModalRoute.of(context);
    if (modalRoute != null) {
      var arg = modalRoute.settings.arguments;
      if (arg is SportFacility) {
        context
            .read<FacilityDetailsBloc>()
            .add(LoadingFacilityDetailsEvent(arg));
      }
    }
    super.didChangeDependencies();
  }

  @override
  Widget build(BuildContext context) {
    return BlocBuilder<FacilityDetailsBloc, FacilityDetailsState>(
      builder: (context, state) {
        switch (state.status) {
          case FacilityDetailsLoadingStatus.loading:
            return const LoadingView();
          case FacilityDetailsLoadingStatus.loaded:
            var facility = state.facility;
            if (facility == null) {
              return const ErrorView();
            }
            return FacilityDetailsLoadedView(
              facility: facility,
              trainings: state.trainings,
              openHours: state.openHours,
            );
          case FacilityDetailsLoadingStatus.error:
            return const ErrorView();
        }
      },
    );
  }
}
