// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'parameter.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_Parameter _$$_ParameterFromJson(Map<String, dynamic> json) => _$_Parameter(
      name: json['name'] as String,
      description: json['description'] as String,
      type: json['type'] as String,
      multivalued: json['multivalued'] as bool,
      mandatory: json['mandatory'] as bool,
      value: json['value'],
    );

Map<String, dynamic> _$$_ParameterToJson(_$_Parameter instance) =>
    <String, dynamic>{
      'name': instance.name,
      'description': instance.description,
      'type': instance.type,
      'multivalued': instance.multivalued,
      'mandatory': instance.mandatory,
      'value': instance.value,
    };
