// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'url_state.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
class _$UrlStateTearOff {
  const _$UrlStateTearOff();

  UrlStateInitial initial() {
    return const UrlStateInitial();
  }

  UrlStateError error(
      {required String url, required int port, required String error}) {
    return UrlStateError(
      url: url,
      port: port,
      error: error,
    );
  }

  UrlStateLoaded loaded(
      {required String url, required int port, required String route}) {
    return UrlStateLoaded(
      url: url,
      port: port,
      route: route,
    );
  }
}

/// @nodoc
const $UrlState = _$UrlStateTearOff();

/// @nodoc
mixin _$UrlState {
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() initial,
    required TResult Function(String url, int port, String error) error,
    required TResult Function(String url, int port, String route) loaded,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function(String url, int port, String error)? error,
    TResult Function(String url, int port, String route)? loaded,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function(String url, int port, String error)? error,
    TResult Function(String url, int port, String route)? loaded,
    required TResult orElse(),
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(UrlStateInitial value) initial,
    required TResult Function(UrlStateError value) error,
    required TResult Function(UrlStateLoaded value) loaded,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(UrlStateInitial value)? initial,
    TResult Function(UrlStateError value)? error,
    TResult Function(UrlStateLoaded value)? loaded,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(UrlStateInitial value)? initial,
    TResult Function(UrlStateError value)? error,
    TResult Function(UrlStateLoaded value)? loaded,
    required TResult orElse(),
  }) =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $UrlStateCopyWith<$Res> {
  factory $UrlStateCopyWith(UrlState value, $Res Function(UrlState) then) =
      _$UrlStateCopyWithImpl<$Res>;
}

/// @nodoc
class _$UrlStateCopyWithImpl<$Res> implements $UrlStateCopyWith<$Res> {
  _$UrlStateCopyWithImpl(this._value, this._then);

  final UrlState _value;
  // ignore: unused_field
  final $Res Function(UrlState) _then;
}

/// @nodoc
abstract class $UrlStateInitialCopyWith<$Res> {
  factory $UrlStateInitialCopyWith(
          UrlStateInitial value, $Res Function(UrlStateInitial) then) =
      _$UrlStateInitialCopyWithImpl<$Res>;
}

/// @nodoc
class _$UrlStateInitialCopyWithImpl<$Res> extends _$UrlStateCopyWithImpl<$Res>
    implements $UrlStateInitialCopyWith<$Res> {
  _$UrlStateInitialCopyWithImpl(
      UrlStateInitial _value, $Res Function(UrlStateInitial) _then)
      : super(_value, (v) => _then(v as UrlStateInitial));

  @override
  UrlStateInitial get _value => super._value as UrlStateInitial;
}

/// @nodoc

class _$UrlStateInitial implements UrlStateInitial {
  const _$UrlStateInitial();

  @override
  String toString() {
    return 'UrlState.initial()';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType && other is UrlStateInitial);
  }

  @override
  int get hashCode => runtimeType.hashCode;

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() initial,
    required TResult Function(String url, int port, String error) error,
    required TResult Function(String url, int port, String route) loaded,
  }) {
    return initial();
  }

  @override
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function(String url, int port, String error)? error,
    TResult Function(String url, int port, String route)? loaded,
  }) {
    return initial?.call();
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function(String url, int port, String error)? error,
    TResult Function(String url, int port, String route)? loaded,
    required TResult orElse(),
  }) {
    if (initial != null) {
      return initial();
    }
    return orElse();
  }

  @override
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(UrlStateInitial value) initial,
    required TResult Function(UrlStateError value) error,
    required TResult Function(UrlStateLoaded value) loaded,
  }) {
    return initial(this);
  }

  @override
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(UrlStateInitial value)? initial,
    TResult Function(UrlStateError value)? error,
    TResult Function(UrlStateLoaded value)? loaded,
  }) {
    return initial?.call(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(UrlStateInitial value)? initial,
    TResult Function(UrlStateError value)? error,
    TResult Function(UrlStateLoaded value)? loaded,
    required TResult orElse(),
  }) {
    if (initial != null) {
      return initial(this);
    }
    return orElse();
  }
}

abstract class UrlStateInitial implements UrlState {
  const factory UrlStateInitial() = _$UrlStateInitial;
}

/// @nodoc
abstract class $UrlStateErrorCopyWith<$Res> {
  factory $UrlStateErrorCopyWith(
          UrlStateError value, $Res Function(UrlStateError) then) =
      _$UrlStateErrorCopyWithImpl<$Res>;
  $Res call({String url, int port, String error});
}

/// @nodoc
class _$UrlStateErrorCopyWithImpl<$Res> extends _$UrlStateCopyWithImpl<$Res>
    implements $UrlStateErrorCopyWith<$Res> {
  _$UrlStateErrorCopyWithImpl(
      UrlStateError _value, $Res Function(UrlStateError) _then)
      : super(_value, (v) => _then(v as UrlStateError));

  @override
  UrlStateError get _value => super._value as UrlStateError;

  @override
  $Res call({
    Object? url = freezed,
    Object? port = freezed,
    Object? error = freezed,
  }) {
    return _then(UrlStateError(
      url: url == freezed
          ? _value.url
          : url // ignore: cast_nullable_to_non_nullable
              as String,
      port: port == freezed
          ? _value.port
          : port // ignore: cast_nullable_to_non_nullable
              as int,
      error: error == freezed
          ? _value.error
          : error // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc

class _$UrlStateError implements UrlStateError {
  const _$UrlStateError(
      {required this.url, required this.port, required this.error});

  @override
  final String url;
  @override
  final int port;
  @override
  final String error;

  @override
  String toString() {
    return 'UrlState.error(url: $url, port: $port, error: $error)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is UrlStateError &&
            (identical(other.url, url) || other.url == url) &&
            (identical(other.port, port) || other.port == port) &&
            (identical(other.error, error) || other.error == error));
  }

  @override
  int get hashCode => Object.hash(runtimeType, url, port, error);

  @JsonKey(ignore: true)
  @override
  $UrlStateErrorCopyWith<UrlStateError> get copyWith =>
      _$UrlStateErrorCopyWithImpl<UrlStateError>(this, _$identity);

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() initial,
    required TResult Function(String url, int port, String error) error,
    required TResult Function(String url, int port, String route) loaded,
  }) {
    return error(url, port, this.error);
  }

  @override
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function(String url, int port, String error)? error,
    TResult Function(String url, int port, String route)? loaded,
  }) {
    return error?.call(url, port, this.error);
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function(String url, int port, String error)? error,
    TResult Function(String url, int port, String route)? loaded,
    required TResult orElse(),
  }) {
    if (error != null) {
      return error(url, port, this.error);
    }
    return orElse();
  }

  @override
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(UrlStateInitial value) initial,
    required TResult Function(UrlStateError value) error,
    required TResult Function(UrlStateLoaded value) loaded,
  }) {
    return error(this);
  }

  @override
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(UrlStateInitial value)? initial,
    TResult Function(UrlStateError value)? error,
    TResult Function(UrlStateLoaded value)? loaded,
  }) {
    return error?.call(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(UrlStateInitial value)? initial,
    TResult Function(UrlStateError value)? error,
    TResult Function(UrlStateLoaded value)? loaded,
    required TResult orElse(),
  }) {
    if (error != null) {
      return error(this);
    }
    return orElse();
  }
}

abstract class UrlStateError implements UrlState {
  const factory UrlStateError(
      {required String url,
      required int port,
      required String error}) = _$UrlStateError;

  String get url;
  int get port;
  String get error;
  @JsonKey(ignore: true)
  $UrlStateErrorCopyWith<UrlStateError> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $UrlStateLoadedCopyWith<$Res> {
  factory $UrlStateLoadedCopyWith(
          UrlStateLoaded value, $Res Function(UrlStateLoaded) then) =
      _$UrlStateLoadedCopyWithImpl<$Res>;
  $Res call({String url, int port, String route});
}

/// @nodoc
class _$UrlStateLoadedCopyWithImpl<$Res> extends _$UrlStateCopyWithImpl<$Res>
    implements $UrlStateLoadedCopyWith<$Res> {
  _$UrlStateLoadedCopyWithImpl(
      UrlStateLoaded _value, $Res Function(UrlStateLoaded) _then)
      : super(_value, (v) => _then(v as UrlStateLoaded));

  @override
  UrlStateLoaded get _value => super._value as UrlStateLoaded;

  @override
  $Res call({
    Object? url = freezed,
    Object? port = freezed,
    Object? route = freezed,
  }) {
    return _then(UrlStateLoaded(
      url: url == freezed
          ? _value.url
          : url // ignore: cast_nullable_to_non_nullable
              as String,
      port: port == freezed
          ? _value.port
          : port // ignore: cast_nullable_to_non_nullable
              as int,
      route: route == freezed
          ? _value.route
          : route // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc

class _$UrlStateLoaded implements UrlStateLoaded {
  const _$UrlStateLoaded(
      {required this.url, required this.port, required this.route});

  @override
  final String url;
  @override
  final int port;
  @override
  final String route;

  @override
  String toString() {
    return 'UrlState.loaded(url: $url, port: $port, route: $route)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is UrlStateLoaded &&
            (identical(other.url, url) || other.url == url) &&
            (identical(other.port, port) || other.port == port) &&
            (identical(other.route, route) || other.route == route));
  }

  @override
  int get hashCode => Object.hash(runtimeType, url, port, route);

  @JsonKey(ignore: true)
  @override
  $UrlStateLoadedCopyWith<UrlStateLoaded> get copyWith =>
      _$UrlStateLoadedCopyWithImpl<UrlStateLoaded>(this, _$identity);

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() initial,
    required TResult Function(String url, int port, String error) error,
    required TResult Function(String url, int port, String route) loaded,
  }) {
    return loaded(url, port, route);
  }

  @override
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function(String url, int port, String error)? error,
    TResult Function(String url, int port, String route)? loaded,
  }) {
    return loaded?.call(url, port, route);
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function(String url, int port, String error)? error,
    TResult Function(String url, int port, String route)? loaded,
    required TResult orElse(),
  }) {
    if (loaded != null) {
      return loaded(url, port, route);
    }
    return orElse();
  }

  @override
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(UrlStateInitial value) initial,
    required TResult Function(UrlStateError value) error,
    required TResult Function(UrlStateLoaded value) loaded,
  }) {
    return loaded(this);
  }

  @override
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(UrlStateInitial value)? initial,
    TResult Function(UrlStateError value)? error,
    TResult Function(UrlStateLoaded value)? loaded,
  }) {
    return loaded?.call(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(UrlStateInitial value)? initial,
    TResult Function(UrlStateError value)? error,
    TResult Function(UrlStateLoaded value)? loaded,
    required TResult orElse(),
  }) {
    if (loaded != null) {
      return loaded(this);
    }
    return orElse();
  }
}

abstract class UrlStateLoaded implements UrlState {
  const factory UrlStateLoaded(
      {required String url,
      required int port,
      required String route}) = _$UrlStateLoaded;

  String get url;
  int get port;
  String get route;
  @JsonKey(ignore: true)
  $UrlStateLoadedCopyWith<UrlStateLoaded> get copyWith =>
      throw _privateConstructorUsedError;
}
