class SportFacilityNews {
  int? id;
  int? sportFacilitiesId;
  String? title;
  String? description;
  String? imageUrl;

  SportFacilityNews({
    this.id,
    this.sportFacilitiesId,
    this.title,
    this.description,
    this.imageUrl,
  });

  factory SportFacilityNews.fromJson(Map<String, dynamic> json) {
    return SportFacilityNews(
      id: json['id'] as int?,
      sportFacilitiesId: json['sportFacilitiesId'] as int?,
      title: json['title'] as String?,
      description: json['description'] as String?,
      imageUrl: json['imageUrl'] as String?,
    );
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['id'] = id;
    data['sportFacilitiesId'] = sportFacilitiesId;
    data['title'] = title;
    data['description'] = description;
    data['imageUrl'] = imageUrl;

    return data;
  }
}
