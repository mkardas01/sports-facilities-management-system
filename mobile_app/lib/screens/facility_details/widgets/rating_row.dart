import 'package:flutter/material.dart';
import 'package:flutter_rating_stars/flutter_rating_stars.dart';
import 'package:sport_plus/models/rating/object_type.dart';
import 'package:sport_plus/widgets/add_rating_button.dart';

class RatingRow extends StatelessWidget {
  final int rating;
  final int objectId;
  final ObjectType objectType;
  const RatingRow(
      {super.key,
      required this.rating,
      required this.objectId,
      required this.objectType});

  @override
  Widget build(BuildContext context) {
    return Align(
      alignment: Alignment.bottomRight,
      child: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Row(
          children: [
            SizedBox(
              width: MediaQuery.of(context).size.width * 0.3,
              child:
                  AddRatingButton(objectId: objectId, objectType: objectType),
            ),
            Expanded(
              child: Align(
                alignment: Alignment.centerRight,
                child: RatingStars(
                  value: rating.toDouble(),
                  valueLabelVisibility: false,
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
