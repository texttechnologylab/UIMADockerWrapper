import 'package:container_explorer/entities/analysis_engine.dart';
import 'package:freezed_annotation/freezed_annotation.dart';
part 'url_state.freezed.dart';


@freezed
class UrlState with _$UrlState {
  const factory UrlState.initial() = UrlStateInitial;
  const factory UrlState.error({required String url, required int port, required String error}) = UrlStateError;
  const factory UrlState.loaded({
    required String url,
    required int port,
    required String route,
  }) = UrlStateLoaded;
}
