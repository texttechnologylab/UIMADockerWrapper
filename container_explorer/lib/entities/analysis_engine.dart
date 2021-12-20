import 'package:container_explorer/entities/sofa_mappings.dart';
import 'package:freezed_annotation/freezed_annotation.dart';
import './annotator.dart';

part 'analysis_engine.freezed.dart';
part 'analysis_engine.g.dart';

@freezed
class AnalysisEngine with _$AnalysisEngine {
  const factory AnalysisEngine({
    required Annotator? annotator,
    required List<AnalysisEngine>? engines,
    required List<SofaMapping> sofa_mappings,
  }) = _AnalysisEngine;

  const AnalysisEngine._();

  factory AnalysisEngine.fromJson(Map<String, dynamic> json) =>
      _$AnalysisEngineFromJson(json);
}
