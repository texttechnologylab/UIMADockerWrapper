// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'dockerfile_event.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
class _$DockerfileEventTearOff {
  const _$DockerfileEventTearOff();

  DockerfileEventLoad loadDockerfile() {
    return const DockerfileEventLoad();
  }
}

/// @nodoc
const $DockerfileEvent = _$DockerfileEventTearOff();

/// @nodoc
mixin _$DockerfileEvent {
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() loadDockerfile,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? loadDockerfile,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? loadDockerfile,
    required TResult orElse(),
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(DockerfileEventLoad value) loadDockerfile,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(DockerfileEventLoad value)? loadDockerfile,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(DockerfileEventLoad value)? loadDockerfile,
    required TResult orElse(),
  }) =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $DockerfileEventCopyWith<$Res> {
  factory $DockerfileEventCopyWith(
          DockerfileEvent value, $Res Function(DockerfileEvent) then) =
      _$DockerfileEventCopyWithImpl<$Res>;
}

/// @nodoc
class _$DockerfileEventCopyWithImpl<$Res>
    implements $DockerfileEventCopyWith<$Res> {
  _$DockerfileEventCopyWithImpl(this._value, this._then);

  final DockerfileEvent _value;
  // ignore: unused_field
  final $Res Function(DockerfileEvent) _then;
}

/// @nodoc
abstract class $DockerfileEventLoadCopyWith<$Res> {
  factory $DockerfileEventLoadCopyWith(
          DockerfileEventLoad value, $Res Function(DockerfileEventLoad) then) =
      _$DockerfileEventLoadCopyWithImpl<$Res>;
}

/// @nodoc
class _$DockerfileEventLoadCopyWithImpl<$Res>
    extends _$DockerfileEventCopyWithImpl<$Res>
    implements $DockerfileEventLoadCopyWith<$Res> {
  _$DockerfileEventLoadCopyWithImpl(
      DockerfileEventLoad _value, $Res Function(DockerfileEventLoad) _then)
      : super(_value, (v) => _then(v as DockerfileEventLoad));

  @override
  DockerfileEventLoad get _value => super._value as DockerfileEventLoad;
}

/// @nodoc

class _$DockerfileEventLoad implements DockerfileEventLoad {
  const _$DockerfileEventLoad();

  @override
  String toString() {
    return 'DockerfileEvent.loadDockerfile()';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType && other is DockerfileEventLoad);
  }

  @override
  int get hashCode => runtimeType.hashCode;

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() loadDockerfile,
  }) {
    return loadDockerfile();
  }

  @override
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? loadDockerfile,
  }) {
    return loadDockerfile?.call();
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? loadDockerfile,
    required TResult orElse(),
  }) {
    if (loadDockerfile != null) {
      return loadDockerfile();
    }
    return orElse();
  }

  @override
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(DockerfileEventLoad value) loadDockerfile,
  }) {
    return loadDockerfile(this);
  }

  @override
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(DockerfileEventLoad value)? loadDockerfile,
  }) {
    return loadDockerfile?.call(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(DockerfileEventLoad value)? loadDockerfile,
    required TResult orElse(),
  }) {
    if (loadDockerfile != null) {
      return loadDockerfile(this);
    }
    return orElse();
  }
}

abstract class DockerfileEventLoad implements DockerfileEvent {
  const factory DockerfileEventLoad() = _$DockerfileEventLoad;
}
