// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'url_event.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
class _$UrlEventTearOff {
  const _$UrlEventTearOff();

  UrlEventLoaded loaded(String url, int port) {
    return UrlEventLoaded(
      url,
      port,
    );
  }

  UrlEventDisconnect disconnect() {
    return const UrlEventDisconnect();
  }

  UrlEventSetRoute setRoute(String route) {
    return UrlEventSetRoute(
      route,
    );
  }
}

/// @nodoc
const $UrlEvent = _$UrlEventTearOff();

/// @nodoc
mixin _$UrlEvent {
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function(String url, int port) loaded,
    required TResult Function() disconnect,
    required TResult Function(String route) setRoute,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function(String url, int port)? loaded,
    TResult Function()? disconnect,
    TResult Function(String route)? setRoute,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function(String url, int port)? loaded,
    TResult Function()? disconnect,
    TResult Function(String route)? setRoute,
    required TResult orElse(),
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(UrlEventLoaded value) loaded,
    required TResult Function(UrlEventDisconnect value) disconnect,
    required TResult Function(UrlEventSetRoute value) setRoute,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(UrlEventLoaded value)? loaded,
    TResult Function(UrlEventDisconnect value)? disconnect,
    TResult Function(UrlEventSetRoute value)? setRoute,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(UrlEventLoaded value)? loaded,
    TResult Function(UrlEventDisconnect value)? disconnect,
    TResult Function(UrlEventSetRoute value)? setRoute,
    required TResult orElse(),
  }) =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $UrlEventCopyWith<$Res> {
  factory $UrlEventCopyWith(UrlEvent value, $Res Function(UrlEvent) then) =
      _$UrlEventCopyWithImpl<$Res>;
}

/// @nodoc
class _$UrlEventCopyWithImpl<$Res> implements $UrlEventCopyWith<$Res> {
  _$UrlEventCopyWithImpl(this._value, this._then);

  final UrlEvent _value;
  // ignore: unused_field
  final $Res Function(UrlEvent) _then;
}

/// @nodoc
abstract class $UrlEventLoadedCopyWith<$Res> {
  factory $UrlEventLoadedCopyWith(
          UrlEventLoaded value, $Res Function(UrlEventLoaded) then) =
      _$UrlEventLoadedCopyWithImpl<$Res>;
  $Res call({String url, int port});
}

/// @nodoc
class _$UrlEventLoadedCopyWithImpl<$Res> extends _$UrlEventCopyWithImpl<$Res>
    implements $UrlEventLoadedCopyWith<$Res> {
  _$UrlEventLoadedCopyWithImpl(
      UrlEventLoaded _value, $Res Function(UrlEventLoaded) _then)
      : super(_value, (v) => _then(v as UrlEventLoaded));

  @override
  UrlEventLoaded get _value => super._value as UrlEventLoaded;

  @override
  $Res call({
    Object? url = freezed,
    Object? port = freezed,
  }) {
    return _then(UrlEventLoaded(
      url == freezed
          ? _value.url
          : url // ignore: cast_nullable_to_non_nullable
              as String,
      port == freezed
          ? _value.port
          : port // ignore: cast_nullable_to_non_nullable
              as int,
    ));
  }
}

/// @nodoc

class _$UrlEventLoaded implements UrlEventLoaded {
  const _$UrlEventLoaded(this.url, this.port);

  @override
  final String url;
  @override
  final int port;

  @override
  String toString() {
    return 'UrlEvent.loaded(url: $url, port: $port)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is UrlEventLoaded &&
            (identical(other.url, url) || other.url == url) &&
            (identical(other.port, port) || other.port == port));
  }

  @override
  int get hashCode => Object.hash(runtimeType, url, port);

  @JsonKey(ignore: true)
  @override
  $UrlEventLoadedCopyWith<UrlEventLoaded> get copyWith =>
      _$UrlEventLoadedCopyWithImpl<UrlEventLoaded>(this, _$identity);

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function(String url, int port) loaded,
    required TResult Function() disconnect,
    required TResult Function(String route) setRoute,
  }) {
    return loaded(url, port);
  }

  @override
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function(String url, int port)? loaded,
    TResult Function()? disconnect,
    TResult Function(String route)? setRoute,
  }) {
    return loaded?.call(url, port);
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function(String url, int port)? loaded,
    TResult Function()? disconnect,
    TResult Function(String route)? setRoute,
    required TResult orElse(),
  }) {
    if (loaded != null) {
      return loaded(url, port);
    }
    return orElse();
  }

  @override
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(UrlEventLoaded value) loaded,
    required TResult Function(UrlEventDisconnect value) disconnect,
    required TResult Function(UrlEventSetRoute value) setRoute,
  }) {
    return loaded(this);
  }

  @override
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(UrlEventLoaded value)? loaded,
    TResult Function(UrlEventDisconnect value)? disconnect,
    TResult Function(UrlEventSetRoute value)? setRoute,
  }) {
    return loaded?.call(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(UrlEventLoaded value)? loaded,
    TResult Function(UrlEventDisconnect value)? disconnect,
    TResult Function(UrlEventSetRoute value)? setRoute,
    required TResult orElse(),
  }) {
    if (loaded != null) {
      return loaded(this);
    }
    return orElse();
  }
}

abstract class UrlEventLoaded implements UrlEvent {
  const factory UrlEventLoaded(String url, int port) = _$UrlEventLoaded;

  String get url;
  int get port;
  @JsonKey(ignore: true)
  $UrlEventLoadedCopyWith<UrlEventLoaded> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $UrlEventDisconnectCopyWith<$Res> {
  factory $UrlEventDisconnectCopyWith(
          UrlEventDisconnect value, $Res Function(UrlEventDisconnect) then) =
      _$UrlEventDisconnectCopyWithImpl<$Res>;
}

/// @nodoc
class _$UrlEventDisconnectCopyWithImpl<$Res>
    extends _$UrlEventCopyWithImpl<$Res>
    implements $UrlEventDisconnectCopyWith<$Res> {
  _$UrlEventDisconnectCopyWithImpl(
      UrlEventDisconnect _value, $Res Function(UrlEventDisconnect) _then)
      : super(_value, (v) => _then(v as UrlEventDisconnect));

  @override
  UrlEventDisconnect get _value => super._value as UrlEventDisconnect;
}

/// @nodoc

class _$UrlEventDisconnect implements UrlEventDisconnect {
  const _$UrlEventDisconnect();

  @override
  String toString() {
    return 'UrlEvent.disconnect()';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType && other is UrlEventDisconnect);
  }

  @override
  int get hashCode => runtimeType.hashCode;

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function(String url, int port) loaded,
    required TResult Function() disconnect,
    required TResult Function(String route) setRoute,
  }) {
    return disconnect();
  }

  @override
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function(String url, int port)? loaded,
    TResult Function()? disconnect,
    TResult Function(String route)? setRoute,
  }) {
    return disconnect?.call();
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function(String url, int port)? loaded,
    TResult Function()? disconnect,
    TResult Function(String route)? setRoute,
    required TResult orElse(),
  }) {
    if (disconnect != null) {
      return disconnect();
    }
    return orElse();
  }

  @override
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(UrlEventLoaded value) loaded,
    required TResult Function(UrlEventDisconnect value) disconnect,
    required TResult Function(UrlEventSetRoute value) setRoute,
  }) {
    return disconnect(this);
  }

  @override
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(UrlEventLoaded value)? loaded,
    TResult Function(UrlEventDisconnect value)? disconnect,
    TResult Function(UrlEventSetRoute value)? setRoute,
  }) {
    return disconnect?.call(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(UrlEventLoaded value)? loaded,
    TResult Function(UrlEventDisconnect value)? disconnect,
    TResult Function(UrlEventSetRoute value)? setRoute,
    required TResult orElse(),
  }) {
    if (disconnect != null) {
      return disconnect(this);
    }
    return orElse();
  }
}

abstract class UrlEventDisconnect implements UrlEvent {
  const factory UrlEventDisconnect() = _$UrlEventDisconnect;
}

/// @nodoc
abstract class $UrlEventSetRouteCopyWith<$Res> {
  factory $UrlEventSetRouteCopyWith(
          UrlEventSetRoute value, $Res Function(UrlEventSetRoute) then) =
      _$UrlEventSetRouteCopyWithImpl<$Res>;
  $Res call({String route});
}

/// @nodoc
class _$UrlEventSetRouteCopyWithImpl<$Res> extends _$UrlEventCopyWithImpl<$Res>
    implements $UrlEventSetRouteCopyWith<$Res> {
  _$UrlEventSetRouteCopyWithImpl(
      UrlEventSetRoute _value, $Res Function(UrlEventSetRoute) _then)
      : super(_value, (v) => _then(v as UrlEventSetRoute));

  @override
  UrlEventSetRoute get _value => super._value as UrlEventSetRoute;

  @override
  $Res call({
    Object? route = freezed,
  }) {
    return _then(UrlEventSetRoute(
      route == freezed
          ? _value.route
          : route // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc

class _$UrlEventSetRoute implements UrlEventSetRoute {
  const _$UrlEventSetRoute(this.route);

  @override
  final String route;

  @override
  String toString() {
    return 'UrlEvent.setRoute(route: $route)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is UrlEventSetRoute &&
            (identical(other.route, route) || other.route == route));
  }

  @override
  int get hashCode => Object.hash(runtimeType, route);

  @JsonKey(ignore: true)
  @override
  $UrlEventSetRouteCopyWith<UrlEventSetRoute> get copyWith =>
      _$UrlEventSetRouteCopyWithImpl<UrlEventSetRoute>(this, _$identity);

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function(String url, int port) loaded,
    required TResult Function() disconnect,
    required TResult Function(String route) setRoute,
  }) {
    return setRoute(route);
  }

  @override
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function(String url, int port)? loaded,
    TResult Function()? disconnect,
    TResult Function(String route)? setRoute,
  }) {
    return setRoute?.call(route);
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function(String url, int port)? loaded,
    TResult Function()? disconnect,
    TResult Function(String route)? setRoute,
    required TResult orElse(),
  }) {
    if (setRoute != null) {
      return setRoute(route);
    }
    return orElse();
  }

  @override
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(UrlEventLoaded value) loaded,
    required TResult Function(UrlEventDisconnect value) disconnect,
    required TResult Function(UrlEventSetRoute value) setRoute,
  }) {
    return setRoute(this);
  }

  @override
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(UrlEventLoaded value)? loaded,
    TResult Function(UrlEventDisconnect value)? disconnect,
    TResult Function(UrlEventSetRoute value)? setRoute,
  }) {
    return setRoute?.call(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(UrlEventLoaded value)? loaded,
    TResult Function(UrlEventDisconnect value)? disconnect,
    TResult Function(UrlEventSetRoute value)? setRoute,
    required TResult orElse(),
  }) {
    if (setRoute != null) {
      return setRoute(this);
    }
    return orElse();
  }
}

abstract class UrlEventSetRoute implements UrlEvent {
  const factory UrlEventSetRoute(String route) = _$UrlEventSetRoute;

  String get route;
  @JsonKey(ignore: true)
  $UrlEventSetRouteCopyWith<UrlEventSetRoute> get copyWith =>
      throw _privateConstructorUsedError;
}
