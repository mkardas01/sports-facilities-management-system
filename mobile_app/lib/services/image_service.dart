import 'dart:io';

import 'package:path/path.dart' as path;
import 'package:image_picker/image_picker.dart';
import 'package:path_provider/path_provider.dart';

class ImageService {
  Future<File?> selectPicture() async {
    final XFile? image =
        await ImagePicker().pickImage(source: ImageSource.gallery);

    if (image == null) {
      return null;
    }

    return _convertToFile(image);
  }

  Future<File?> takePicture() async {
    final XFile? photo =
        await ImagePicker().pickImage(source: ImageSource.camera);
    if (photo == null) {
      return null;
    }
    return _convertToFile(photo);
  }

  File _convertToFile(XFile xFile) {
    return File(xFile.path);
  }

  Future<String> saveImage(File pickedFile) async {
    final directory = await getApplicationSupportDirectory();
    const String fileName = 'avatar.png';
    final String filePath = path.join(directory.path, fileName);

    final File imageFile = File(pickedFile.path);
    final File savedImage = await imageFile.copy(filePath);

    print('Obraz zapisany w: ${savedImage.path}');
    return savedImage.path;
  }

  Future<File?> openImage(String? filePath) async {
    if (filePath == null) {
      return null;
    }
    try {
      final File imageFile = File(filePath);

      if (await imageFile.exists()) {
        return imageFile;
      } else {
        return null;
      }
    } catch (e) {
      print("Błąd podczas odczytywania pliku: $e");
      return null;
    }
  }
}
