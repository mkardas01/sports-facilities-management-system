import 'package:sport_plus/models/rating.dart';

class Coach {
  final int id;
  final String name;
  final String surname;
  final String imageUrl;
  final Rating rating;

  Coach(
      {required this.id,
      required this.name,
      required this.surname,
      required this.imageUrl,
      required this.rating});
}
