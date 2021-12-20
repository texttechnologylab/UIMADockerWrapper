import 'package:freezed_annotation/freezed_annotation.dart';

part 'type.freezed.dart';
part 'type.g.dart';

@freezed
class UIMAType with _$UIMAType {
  const factory UIMAType({
    required String name,
    required String? description,
    required List<UIMAType> children,
  }) = _UIMAType;

  const UIMAType._();

  factory UIMAType.fromJson(Map<String, dynamic> json) =>
      _$UIMATypeFromJson(json);
}
