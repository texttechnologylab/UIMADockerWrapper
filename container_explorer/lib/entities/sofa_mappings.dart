import 'package:freezed_annotation/freezed_annotation.dart';

part 'sofa_mappings.freezed.dart';
part 'sofa_mappings.g.dart';

@freezed
class SofaMapping with _$SofaMapping {
  const factory SofaMapping({
    required String component_sofa,
    required String aggregate_sofa,
  }) = _SofaMapping;

  const SofaMapping._();

  factory SofaMapping.fromJson(Map<String, dynamic> json) =>
      _$SofaMappingFromJson(json);
}
