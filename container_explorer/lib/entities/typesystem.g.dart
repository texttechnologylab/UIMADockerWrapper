// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'typesystem.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_Typesystem _$$_TypesystemFromJson(Map<String, dynamic> json) =>
    _$_Typesystem(
      typesystem: (json['typesystem'] as List<dynamic>)
          .map((e) => UIMAType.fromJson(e as Map<String, dynamic>))
          .toList(),
    );

Map<String, dynamic> _$$_TypesystemToJson(_$_Typesystem instance) =>
    <String, dynamic>{
      'typesystem': instance.typesystem,
    };
