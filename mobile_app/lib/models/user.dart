class User {
  final int id;
  String? name;
  String? surname;
  final String? email;
  String? imageUrl;

  User({
    required this.id,
    required this.name,
    required this.surname,
    required this.email,
    required this.imageUrl,
  });

  String get getInitials {
    if (name != null && surname != null) {
      return "${name![0]}${surname![0]}";
    }
    return "-";
  }
}
