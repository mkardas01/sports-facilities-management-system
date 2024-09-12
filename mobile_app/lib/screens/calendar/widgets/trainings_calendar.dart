import 'package:flutter/material.dart';
import 'package:sport_plus/config/app_colors.dart';
import 'package:sport_plus/config/app_typography.dart';
import 'package:sport_plus/models/details/training_session.dart';
import 'package:table_calendar/table_calendar.dart';

class TrainingsCalendar extends StatelessWidget {
  final Map<DateTime, List<TrainingSession>> trainings;
  final DateTime selectedDay;
  final DateTime focusedDay;
  final void Function(DateTime) onChanged;
  const TrainingsCalendar(
      {super.key,
      required this.trainings,
      required this.focusedDay,
      required this.selectedDay,
      required this.onChanged});

  @override
  Widget build(BuildContext context) {
    return TableCalendar(
      eventLoader: (day) {
        return trainings[day] ?? [];
      },
      headerStyle:
          const HeaderStyle(titleCentered: true, formatButtonVisible: false),
      calendarStyle: CalendarStyle(
          todayDecoration: const BoxDecoration(
            color: AppColors.mainColor,
            shape: BoxShape.circle,
          ),
          selectedDecoration: BoxDecoration(
            shape: BoxShape.circle,
            border: Border.all(
              width: 1,
              color: AppColors.mainColor,
            ),
          ),
          selectedTextStyle: AppTypography.defaultTextStyle),
      locale: 'pl_PL',
      firstDay: DateTime(2010),
      lastDay: DateTime(2040),
      focusedDay: focusedDay,
      selectedDayPredicate: (day) => isSameDay(selectedDay, day),
      onDaySelected: (newSelectedDay, newFocusedDay) =>
          onChanged(newSelectedDay),
      calendarBuilders: CalendarBuilders(
        markerBuilder: (context, day, events) {
          if (events.isNotEmpty) {
            return Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                ...events.map((event) {
                  return Container(
                    margin: const EdgeInsets.symmetric(horizontal: 1.5),
                    decoration: const BoxDecoration(
                      shape: BoxShape.circle,
                      color: Colors.black,
                    ),
                    width: 8,
                    height: 8,
                  );
                })
              ],
            );
          }
          return null;
        },
      ),
    );
  }
}
