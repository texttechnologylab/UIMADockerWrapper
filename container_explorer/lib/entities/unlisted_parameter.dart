import 'package:freezed_annotation/freezed_annotation.dart';

part 'unlisted_parameter.freezed.dart';
part 'unlisted_parameter.g.dart';

@freezed
class UnlistedParameter with _$UnlistedParameter {
  const factory UnlistedParameter({
    required String name,
    required dynamic value,
  }) = _UnlistedParameter;

  const UnlistedParameter._();

  factory UnlistedParameter.fromJson(Map<String, dynamic> json) =>
      _$UnlistedParameterFromJson(json);
}
