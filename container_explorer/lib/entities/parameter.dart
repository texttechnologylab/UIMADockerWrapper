import 'package:freezed_annotation/freezed_annotation.dart';

part 'parameter.freezed.dart';
part 'parameter.g.dart';

@freezed
class Parameter with _$Parameter {
  const factory Parameter({
    required String name,
    required String description,
    required String type,
    required bool multivalued,
    required bool mandatory,
    required dynamic value,
  }) = _Parameter;

  const Parameter._();

  factory Parameter.fromJson(Map<String, dynamic> json) =>
      _$ParameterFromJson(json);
}
