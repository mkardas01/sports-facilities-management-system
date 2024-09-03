import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:sport_plus/config/app_colors.dart';
import 'package:sport_plus/config/app_strings.dart';
import 'package:sport_plus/config/app_typography.dart';
import 'package:sport_plus/screens/home/home_screen.dart';
import 'package:sport_plus/screens/splash/bloc/sign_in_bloc.dart';
import 'package:sport_plus/utils/form_validators.dart';
import 'package:sport_plus/widgets/app_scaffold.dart';
import 'package:sport_plus/widgets/generic_button.dart';
import 'package:sport_plus/widgets/loading_dialog.dart';

class SignInScreen extends StatefulWidget {
  const SignInScreen({super.key});
  static const String route = "/sign-in";

  @override
  State<SignInScreen> createState() => _SignInScreenState();
}

class _SignInScreenState extends State<SignInScreen> {
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();
  @override
  Widget build(BuildContext context) {
    return BlocListener<SignInBloc, SignInState>(
      listener: (context, state) {
        switch (state.status) {
          case SignInLoadingStatus.error:
            Navigator.pop(context);
            Fluttertoast.showToast(
                msg: AppStrings.error,
                toastLength: Toast.LENGTH_SHORT,
                gravity: ToastGravity.BOTTOM,
                timeInSecForIosWeb: 2,
                backgroundColor: Colors.red,
                textColor: Colors.black,
                fontSize: 16.0);
            break;
          case SignInLoadingStatus.idle:
            break;
          case SignInLoadingStatus.loading:
            showDialog(
              context: context,
              builder: (context) => const LoadingDialog(),
            );
            break;
          case SignInLoadingStatus.loggedIn:
            Navigator.popUntil(context, (route) => false);
            Navigator.pushNamed(context, HomeScreen.route);
        }
      },
      child: AppScaffold(
        child: Form(
          key: _formKey,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              TextFormField(
                validator: (value) =>
                    FormValidators.notEmpty(_passwordController.text),
                controller: _emailController,
                decoration: InputDecoration(
                  label: Text(
                    AppStrings.email,
                    style: AppTypography.defaultBoldTextStyle(
                        color: AppColors.mainColor),
                  ),
                ),
              ),
              TextFormField(
                obscureText: true,
                validator: (value) =>
                    FormValidators.notEmpty(_passwordController.text),
                controller: _passwordController,
                decoration: InputDecoration(
                  label: Text(
                    AppStrings.password,
                    style: AppTypography.defaultBoldTextStyle(
                        color: AppColors.mainColor),
                  ),
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(top: 30),
                child: GenericButton(
                  onTap: () {
                    if (_formKey.currentState?.validate() ?? false) {
                      context.read<SignInBloc>().add(LogInEvent(
                          _emailController.text, _passwordController.text));
                    }
                  },
                  title: AppStrings.signIn,
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}
