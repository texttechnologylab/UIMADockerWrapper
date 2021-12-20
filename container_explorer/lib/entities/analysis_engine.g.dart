// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'analysis_engine.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_AnalysisEngine _$$_AnalysisEngineFromJson(Map<String, dynamic> json) =>
    _$_AnalysisEngine(
      annotator: json['annotator'] == null
          ? null
          : Annotator.fromJson(json['annotator'] as Map<String, dynamic>),
      engines: (json['engines'] as List<dynamic>?)
          ?.map((e) => AnalysisEngine.fromJson(e as Map<String, dynamic>))
          .toList(),
      sofa_mappings: (json['sofa_mappings'] as List<dynamic>)
          .map((e) => SofaMapping.fromJson(e as Map<String, dynamic>))
          .toList(),
    );

Map<String, dynamic> _$$_AnalysisEngineToJson(_$_AnalysisEngine instance) =>
    <String, dynamic>{
      'annotator': instance.annotator,
      'engines': instance.engines,
      'sofa_mappings': instance.sofa_mappings,
    };
