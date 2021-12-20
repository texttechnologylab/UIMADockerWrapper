import 'package:freezed_annotation/freezed_annotation.dart';
import './type.dart';


part 'typesystem.freezed.dart';
part 'typesystem.g.dart';

@freezed
class Typesystem with _$Typesystem {
  const factory Typesystem({
    required List<UIMAType> typesystem,
  }) = _Typesystem;

  const Typesystem._();

  factory Typesystem.fromJson(Map<String, dynamic> json) =>
      _$TypesystemFromJson(json);
}
