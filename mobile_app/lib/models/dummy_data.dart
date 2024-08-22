import 'package:sport_plus/models/coach.dart';
import 'package:sport_plus/models/open_hours.dart';
import 'package:sport_plus/models/rating.dart';
import 'package:sport_plus/models/sport_equipment.dart';
import 'package:sport_plus/models/sport_facility.dart';
import 'package:sport_plus/models/sport_facility_news.dart';
import 'package:sport_plus/models/training_session.dart';
import 'package:sport_plus/models/user.dart';

class DummyData {
  static final Rating rating1 = Rating(id: 1, rate: 5);
  static final Rating rating2 = Rating(id: 2, rate: 4);
  static final Rating rating3 = Rating(id: 3, rate: 3);

  static final OpenHours openHours1 = OpenHours(
    monday: "06:00 - 22:00",
    tuesday: "06:00 - 22:00",
    wednesday: "06:00 - 22:00",
    thursday: "06:00 - 22:00",
    friday: "06:00 - 22:00",
    saturday: "08:00 - 20:00",
    sunday: "08:00 - 20:00",
  );

  static final OpenHours openHours2 = OpenHours(
    monday: "08:00 - 20:00",
    tuesday: "08:00 - 20:00",
    wednesday: "08:00 - 20:00",
    thursday: "08:00 - 20:00",
    friday: "08:00 - 20:00",
    saturday: "10:00 - 18:00",
    sunday: "10:00 - 18:00",
  );

  static final SportEquipment equipment1 = SportEquipment(
    id: 1,
    type: "Treadmill",
    brand: "BrandX",
    model: "TX1000",
    description: "High-end treadmill with multiple training programs.",
    imageUrl:
        "https://st4.depositphotos.com/2395803/40369/i/450/depositphotos_403693890-stock-photo-sports-trainer-treadmill-isolated-white.jpg",
  );

  static final SportEquipment equipment2 = SportEquipment(
    id: 2,
    type: "Dumbbells",
    brand: "BrandY",
    model: "DY500",
    description: "Set of adjustable dumbbells.",
    imageUrl:
        "https://as1.ftcdn.net/v2/jpg/02/68/20/24/1000_F_268202443_KkdJz3xUEFXlMYhdUMn1yh77c2a6jt6u.jpg",
  );

  static final SportFacilityNews news1 = SportFacilityNews(
    id: 1,
    title: "New Yoga Classes",
    description:
        "Join our new morning yoga classes every Monday and Wednesday.",
    imageUrl:
        "https://comcomzone.pl/uploads/images/podstrona_nowa_huta_silownia.png",
  );

  static final SportFacilityNews news2 = SportFacilityNews(
    id: 2,
    title: "Renovation Completed",
    description:
        "We are excited to announce the completion of our facility renovation.",
    imageUrl:
        "https://comcomzone.pl/uploads/images/podstrona_nowa_huta_silownia.png",
  );

  static final User user1 = User(
    id: 1,
    name: "Jane",
    surname: "Doe",
    email: "jane.doe@example.com",
    imageUrl: null,
  );

  static final User user2 = User(
    id: 2,
    name: "John",
    surname: "Smith",
    email: "john.smith@example.com",
    imageUrl: "https://example.com/john.png",
  );

  static final Coach coach1 = Coach(
    id: 1,
    name: "Emily",
    surname: "Blake",
    imageUrl: "https://engineering.unl.edu/images/staff/Kayla-Person.jpg",
    rating: rating2,
  );

  static final Coach coach2 = Coach(
    id: 2,
    name: "Michael",
    surname: "Johnson",
    imageUrl:
        "https://www.wilsoncenter.org/sites/default/files/styles/large/public/media/images/person/james-person-1.webp",
    rating: rating2,
  );
  static final TrainingSession session1 = TrainingSession(
    id: 1,
    coach: coach1,
    name: "Morning Yoga",
    startHour: 7,
    duration: 1,
    isWeekly: true,
    trainingDate: DateTime.parse("2024-08-08 07:00:00"),
    capacity: 20,
    freeBooked: true,
    rating: rating3,
    participants: [user1],
  );

  static final TrainingSession session2 = TrainingSession(
    id: 2,
    coach: coach2,
    name: "Evening Pilates",
    startHour: 17,
    duration: 1,
    isWeekly: true,
    trainingDate: DateTime.parse("2024-08-08 07:00:00"),
    capacity: 15,
    freeBooked: false,
    rating: rating3,
    participants: [user2],
  );

  static final SportFacility facility1 = SportFacility(
    id: 1,
    name: "Main Gym",
    description: "A large gym with modern equipment.",
    address: "123 Fitness St.",
    type: "Gym",
    membershipRequired: true,
    imageUrl:
        "https://comcomzone.pl/uploads/images/podstrona_nowa_huta_silownia.png",
    openHours: openHours1,
    rating: rating3,
    news: [news1, news2],
    participants: [user1, user2],
    equipment: [equipment1, equipment2],
    tranings: [session1, session2],
    coaches: [coach1, coach2],
  );

  static final SportFacility facility2 = SportFacility(
    id: 2,
    name: "Yoga Studio",
    description: "A peaceful studio for all your yoga needs.",
    address: "456 Harmony Ave.",
    type: "Studio",
    membershipRequired: false,
    imageUrl:
        "https://thyroset.pl/blog/wp-content/uploads/2020/07/Tarczyca-a-joga-scaled-1200x675.jpeg",
    openHours: openHours2,
    rating: rating2,
    news: [news2],
    participants: [user1],
    equipment: [],
    coaches: [coach2],
    tranings: [session1, session2],
  );

  // Static getters for data retrieval
  static List<SportFacility> getSportFacilities() {
    return [facility1, facility2];
  }

  static List<User> getUsers() {
    return [user1, user2];
  }

  static List<Coach> getCoaches() {
    return [coach1, coach2];
  }

  static List<SportEquipment> getSportEquipment() {
    return [equipment1, equipment2];
  }

  static List<SportFacilityNews> getSportFacilityNews() {
    return [news1, news2];
  }

  static List<Rating> getRatings() {
    return [rating1, rating2, rating3];
  }

  static List<TrainingSession> getTrainingSessions() {
    return [session1, session2];
  }
}
