import 'package:freezed_annotation/freezed_annotation.dart';

import './parameter.dart';
import './unlisted_parameter.dart';


part 'annotator.freezed.dart';
part 'annotator.g.dart';

@freezed
class Annotator with _$Annotator {
  const factory Annotator({
    required String name,
    required List<Parameter> parameters,
    required List<UnlistedParameter> unlisted_parameters,
  }) = _Annotator;

  const Annotator._();

  factory Annotator.fromJson(Map<String, dynamic> json) =>
      _$AnnotatorFromJson(json);
}
