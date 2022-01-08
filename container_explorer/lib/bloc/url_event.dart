import 'package:freezed_annotation/freezed_annotation.dart';
part 'url_event.freezed.dart';


@freezed
class UrlEvent with _$UrlEvent {
  const factory UrlEvent.loaded(String url, int port) = UrlEventLoaded;
  const factory UrlEvent.disconnect() = UrlEventDisconnect;
  const factory UrlEvent.setRoute(String route) = UrlEventSetRoute;
}
