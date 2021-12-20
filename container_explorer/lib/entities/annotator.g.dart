// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'annotator.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_Annotator _$$_AnnotatorFromJson(Map<String, dynamic> json) => _$_Annotator(
      name: json['name'] as String,
      parameters: (json['parameters'] as List<dynamic>)
          .map((e) => Parameter.fromJson(e as Map<String, dynamic>))
          .toList(),
      unlisted_parameters: (json['unlisted_parameters'] as List<dynamic>)
          .map((e) => UnlistedParameter.fromJson(e as Map<String, dynamic>))
          .toList(),
    );

Map<String, dynamic> _$$_AnnotatorToJson(_$_Annotator instance) =>
    <String, dynamic>{
      'name': instance.name,
      'parameters': instance.parameters,
      'unlisted_parameters': instance.unlisted_parameters,
    };
