// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'type.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_UIMAType _$$_UIMATypeFromJson(Map<String, dynamic> json) => _$_UIMAType(
      name: json['name'] as String,
      description: json['description'] as String?,
      children: (json['children'] as List<dynamic>)
          .map((e) => UIMAType.fromJson(e as Map<String, dynamic>))
          .toList(),
    );

Map<String, dynamic> _$$_UIMATypeToJson(_$_UIMAType instance) =>
    <String, dynamic>{
      'name': instance.name,
      'description': instance.description,
      'children': instance.children,
    };
