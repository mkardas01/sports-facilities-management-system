import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_week_view/flutter_week_view.dart';
import 'package:sport_plus/config/app_colors.dart';
import 'package:sport_plus/config/app_strings.dart';
import 'package:sport_plus/screens/facility_details/models/day_training.dart';
import 'package:sport_plus/screens/trainings/bloc/trainings_bloc.dart';
import 'package:sport_plus/screens/trainings/widgets/training_participation_dialog.dart';
import 'package:sport_plus/widgets/app_scaffold.dart';
import 'package:sport_plus/widgets/generic_dialog.dart';
import 'package:sport_plus/widgets/loading_dialog.dart';

class TrainingsScreen extends StatefulWidget {
  const TrainingsScreen({super.key});
  static const String route = "/trainings";

  @override
  State<TrainingsScreen> createState() => _TrainingsScreenState();
}

class _TrainingsScreenState extends State<TrainingsScreen>
    with SingleTickerProviderStateMixin {
  late TabController _tabController;
  DateTime _selectedDate = DateTime.now();
  List<DayTrainings> trainings = [];

  @override
  void initState() {
    DateTime now = DateTime.now();
    _selectedDate = DateTime(now.year, now.month, now.day);
    _tabController = TabController(length: 7, vsync: this);
    _tabController.index = _selectedDate.weekday - 1;

    _tabController.addListener(() {
      if (_tabController.indexIsChanging) {
        _updateDate();
      }
    });
    super.initState();
  }

  @override
  void didChangeDependencies() {
    var args = ModalRoute.of(context)?.settings.arguments;
    if (args is List<DayTrainings>) {
      trainings = args;
    }
    super.didChangeDependencies();
  }

  void _updateDate() {
    final now = DateTime.now();
    final selectedIndex = _tabController.index;
    final startOfWeek = now.subtract(Duration(days: now.weekday - 1));
    final daysOfWeek =
        List.generate(7, (index) => startOfWeek.add(Duration(days: index)));

    setState(() {
      _selectedDate = daysOfWeek[selectedIndex];
    });
  }

  @override
  void dispose() {
    _tabController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return BlocListener<TrainingsBloc, TrainingsState>(
      listener: (context, state) {
        switch (state.status) {
          case TrainingsLoadingStatus.idle:
            break;
          case TrainingsLoadingStatus.saving:
            showDialog(
                context: context, builder: (context) => const LoadingDialog());
          case TrainingsLoadingStatus.saved:
          case TrainingsLoadingStatus.error:
          case TrainingsLoadingStatus.userAlreadySaved:
            Navigator.pop(context);
            showDialog(
              context: context,
              builder: (context) => GenericDialog(
                title: state.status == TrainingsLoadingStatus.saved
                    ? AppStrings.participationSaved
                    : state.status == TrainingsLoadingStatus.userAlreadySaved
                        ? AppStrings.participationAlreadySaved
                        : AppStrings.participationError,
              ),
            );
        }
      },
      child: AppScaffold(
        setPadding: false,
        child: Column(
          children: [
            Container(
              color: AppColors.backgroundColor,
              child: TabBar(
                controller: _tabController,
                labelColor: Colors.white,
                unselectedLabelColor: Colors.black,
                indicatorColor: AppColors.mainColor,
                indicatorSize: TabBarIndicatorSize.tab,
                tabs: const [
                  Tab(text: "Pn"),
                  Tab(text: "Wt"),
                  Tab(text: "Śr"),
                  Tab(text: "Czw"),
                  Tab(text: "Pt"),
                  Tab(text: "Sb"),
                  Tab(text: "N")
                ],
              ),
            ),
            Expanded(
              child: DayView(
                style: const DayViewStyle(
                    backgroundColor: Colors.white,
                    headerSize: 0,
                    currentTimeRuleColor: AppColors.mainColor),
                date: _selectedDate,
                events: [
                  ...trainings.map(
                    (training) => FlutterWeekViewEvent(
                      margin: const EdgeInsets.all(2),
                      decoration: const BoxDecoration(
                          borderRadius: BorderRadius.all(Radius.circular(15)),
                          color: AppColors.mainColor),
                      title: training.title,
                      description: training.description,
                      start: training.start,
                      end: training.end,
                      onTap: () => showDialog(
                        context: context,
                        builder: (ctx) => training.freeBooked
                            ? BlocProvider.value(
                                value: context.read<TrainingsBloc>(),
                                child: TrainingParticipationDialog(
                                  trainingId: training.trainingId,
                                ),
                              )
                            : const GenericDialog(
                                title: AppStrings.trainingNotAvaliable,
                              ),
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
