import 'dart:io';

import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';
import 'package:sport_plus/models/user/user_dto.dart';
import 'package:sport_plus/repository/user_repository.dart';
import 'package:sport_plus/screens/profile/edit_profile/models/image_picker_source.dart';
import 'package:sport_plus/services/image_service.dart';

part 'profile_event.dart';
part 'profile_state.dart';

class ProfileBloc extends Bloc<ProfileEvent, ProfileState> {
  final ImageService imageService;
  final UserRepository userRepository;
  ProfileBloc({required this.imageService, required this.userRepository})
      : super(const ProfileState()) {
    on<InitDateEvent>(_initData);
    on<UpdateProfileEvent>(_updateUser);
    on<PickImageEvent>(_pickImage);
  }
  Future<void> _initData(
      InitDateEvent event, Emitter<ProfileState> emitter) async {
    var user = await userRepository.getCurrentUser();
    if (user == null) {
      emitter(state.copyWith(status: ProfileLoadingStatus.error));
      return;
    }
    var avatarFile = await imageService.openImage(user.imageUrl);
    emitter(state.copyWith(
        status: ProfileLoadingStatus.loaded,
        user: user,
        avatarUrl: user.imageUrl,
        avatarFile: avatarFile));
  }

  Future<void> _updateUser(
      UpdateProfileEvent event, Emitter<ProfileState> emitter) async {
    emitter(state.copyWith(status: ProfileLoadingStatus.saving));
    var updatedUser = state.user;
    updatedUser?.imageUrl = state.avatarUrl;
    updatedUser?.name = event.name;
    updatedUser?.surname = event.surname;
    if (updatedUser == null) {
      emitter(state.copyWith(status: ProfileLoadingStatus.savingError));
      emitter(state.copyWith(status: ProfileLoadingStatus.loaded));
      return;
    }
    var updateResult = await userRepository.updateUser(updatedUser);
    if (updateResult) {
      emitter(state.copyWith(
          status: ProfileLoadingStatus.saved, user: updatedUser));
    } else {
      emitter(state.copyWith(status: ProfileLoadingStatus.savingError));
    }
    emitter(state.copyWith(status: ProfileLoadingStatus.loaded));
  }

  Future<void> _pickImage(
      PickImageEvent event, Emitter<ProfileState> emitter) async {
    File? avatar;
    switch (event.source) {
      case ImagePickerSource.camera:
        avatar = await imageService.takePicture();
        break;
      case ImagePickerSource.gallery:
        avatar = await imageService.selectPicture();
        break;
    }

    if (avatar == null) {
      emitter(state.copyWith(avatarStatus: AvatarLoadingStatus.savingError));
      emitter(state.copyWith(avatarStatus: AvatarLoadingStatus.idle));
      return;
    }
    var url = await imageService.saveImage(avatar);
    emitter(state.copyWith(
        avatarStatus: AvatarLoadingStatus.saved,
        avatarFile: avatar,
        avatarUrl: url));
    emitter(state.copyWith(avatarStatus: AvatarLoadingStatus.idle));
  }
}
