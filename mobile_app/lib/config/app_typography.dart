import 'package:flutter/material.dart';

class AppTypography {
  static TextStyle defaultTextStyle = const TextStyle(fontSize: 16);
  static TextStyle defaultBoldTextStyle({Color? color}) => TextStyle(
      fontSize: 16, fontWeight: FontWeight.bold, color: color ?? Colors.black);
  static TextStyle bigTextStyle = const TextStyle(fontSize: 22);
  static TextStyle bigBoldTextStyle({Color? color}) => TextStyle(
      fontSize: 22, fontWeight: FontWeight.bold, color: color ?? Colors.black);
  static TextStyle littleTextStyle =
      const TextStyle(fontSize: 13, color: Colors.grey);
}
