// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'dockerfile_state.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
class _$DockerfileStateTearOff {
  const _$DockerfileStateTearOff();

  DockerfileStateInitial initial() {
    return const DockerfileStateInitial();
  }

  DockerfileStateLoading loading() {
    return const DockerfileStateLoading();
  }

  DockerfileStateFailure failure() {
    return const DockerfileStateFailure();
  }

  DockerfileStateLoaded loaded({required String dockerfile}) {
    return DockerfileStateLoaded(
      dockerfile: dockerfile,
    );
  }
}

/// @nodoc
const $DockerfileState = _$DockerfileStateTearOff();

/// @nodoc
mixin _$DockerfileState {
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() initial,
    required TResult Function() loading,
    required TResult Function() failure,
    required TResult Function(String dockerfile) loaded,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(String dockerfile)? loaded,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(String dockerfile)? loaded,
    required TResult orElse(),
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(DockerfileStateInitial value) initial,
    required TResult Function(DockerfileStateLoading value) loading,
    required TResult Function(DockerfileStateFailure value) failure,
    required TResult Function(DockerfileStateLoaded value) loaded,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(DockerfileStateInitial value)? initial,
    TResult Function(DockerfileStateLoading value)? loading,
    TResult Function(DockerfileStateFailure value)? failure,
    TResult Function(DockerfileStateLoaded value)? loaded,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(DockerfileStateInitial value)? initial,
    TResult Function(DockerfileStateLoading value)? loading,
    TResult Function(DockerfileStateFailure value)? failure,
    TResult Function(DockerfileStateLoaded value)? loaded,
    required TResult orElse(),
  }) =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $DockerfileStateCopyWith<$Res> {
  factory $DockerfileStateCopyWith(
          DockerfileState value, $Res Function(DockerfileState) then) =
      _$DockerfileStateCopyWithImpl<$Res>;
}

/// @nodoc
class _$DockerfileStateCopyWithImpl<$Res>
    implements $DockerfileStateCopyWith<$Res> {
  _$DockerfileStateCopyWithImpl(this._value, this._then);

  final DockerfileState _value;
  // ignore: unused_field
  final $Res Function(DockerfileState) _then;
}

/// @nodoc
abstract class $DockerfileStateInitialCopyWith<$Res> {
  factory $DockerfileStateInitialCopyWith(DockerfileStateInitial value,
          $Res Function(DockerfileStateInitial) then) =
      _$DockerfileStateInitialCopyWithImpl<$Res>;
}

/// @nodoc
class _$DockerfileStateInitialCopyWithImpl<$Res>
    extends _$DockerfileStateCopyWithImpl<$Res>
    implements $DockerfileStateInitialCopyWith<$Res> {
  _$DockerfileStateInitialCopyWithImpl(DockerfileStateInitial _value,
      $Res Function(DockerfileStateInitial) _then)
      : super(_value, (v) => _then(v as DockerfileStateInitial));

  @override
  DockerfileStateInitial get _value => super._value as DockerfileStateInitial;
}

/// @nodoc

class _$DockerfileStateInitial implements DockerfileStateInitial {
  const _$DockerfileStateInitial();

  @override
  String toString() {
    return 'DockerfileState.initial()';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType && other is DockerfileStateInitial);
  }

  @override
  int get hashCode => runtimeType.hashCode;

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() initial,
    required TResult Function() loading,
    required TResult Function() failure,
    required TResult Function(String dockerfile) loaded,
  }) {
    return initial();
  }

  @override
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(String dockerfile)? loaded,
  }) {
    return initial?.call();
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(String dockerfile)? loaded,
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
    required TResult Function(DockerfileStateInitial value) initial,
    required TResult Function(DockerfileStateLoading value) loading,
    required TResult Function(DockerfileStateFailure value) failure,
    required TResult Function(DockerfileStateLoaded value) loaded,
  }) {
    return initial(this);
  }

  @override
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(DockerfileStateInitial value)? initial,
    TResult Function(DockerfileStateLoading value)? loading,
    TResult Function(DockerfileStateFailure value)? failure,
    TResult Function(DockerfileStateLoaded value)? loaded,
  }) {
    return initial?.call(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(DockerfileStateInitial value)? initial,
    TResult Function(DockerfileStateLoading value)? loading,
    TResult Function(DockerfileStateFailure value)? failure,
    TResult Function(DockerfileStateLoaded value)? loaded,
    required TResult orElse(),
  }) {
    if (initial != null) {
      return initial(this);
    }
    return orElse();
  }
}

abstract class DockerfileStateInitial implements DockerfileState {
  const factory DockerfileStateInitial() = _$DockerfileStateInitial;
}

/// @nodoc
abstract class $DockerfileStateLoadingCopyWith<$Res> {
  factory $DockerfileStateLoadingCopyWith(DockerfileStateLoading value,
          $Res Function(DockerfileStateLoading) then) =
      _$DockerfileStateLoadingCopyWithImpl<$Res>;
}

/// @nodoc
class _$DockerfileStateLoadingCopyWithImpl<$Res>
    extends _$DockerfileStateCopyWithImpl<$Res>
    implements $DockerfileStateLoadingCopyWith<$Res> {
  _$DockerfileStateLoadingCopyWithImpl(DockerfileStateLoading _value,
      $Res Function(DockerfileStateLoading) _then)
      : super(_value, (v) => _then(v as DockerfileStateLoading));

  @override
  DockerfileStateLoading get _value => super._value as DockerfileStateLoading;
}

/// @nodoc

class _$DockerfileStateLoading implements DockerfileStateLoading {
  const _$DockerfileStateLoading();

  @override
  String toString() {
    return 'DockerfileState.loading()';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType && other is DockerfileStateLoading);
  }

  @override
  int get hashCode => runtimeType.hashCode;

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() initial,
    required TResult Function() loading,
    required TResult Function() failure,
    required TResult Function(String dockerfile) loaded,
  }) {
    return loading();
  }

  @override
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(String dockerfile)? loaded,
  }) {
    return loading?.call();
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(String dockerfile)? loaded,
    required TResult orElse(),
  }) {
    if (loading != null) {
      return loading();
    }
    return orElse();
  }

  @override
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(DockerfileStateInitial value) initial,
    required TResult Function(DockerfileStateLoading value) loading,
    required TResult Function(DockerfileStateFailure value) failure,
    required TResult Function(DockerfileStateLoaded value) loaded,
  }) {
    return loading(this);
  }

  @override
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(DockerfileStateInitial value)? initial,
    TResult Function(DockerfileStateLoading value)? loading,
    TResult Function(DockerfileStateFailure value)? failure,
    TResult Function(DockerfileStateLoaded value)? loaded,
  }) {
    return loading?.call(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(DockerfileStateInitial value)? initial,
    TResult Function(DockerfileStateLoading value)? loading,
    TResult Function(DockerfileStateFailure value)? failure,
    TResult Function(DockerfileStateLoaded value)? loaded,
    required TResult orElse(),
  }) {
    if (loading != null) {
      return loading(this);
    }
    return orElse();
  }
}

abstract class DockerfileStateLoading implements DockerfileState {
  const factory DockerfileStateLoading() = _$DockerfileStateLoading;
}

/// @nodoc
abstract class $DockerfileStateFailureCopyWith<$Res> {
  factory $DockerfileStateFailureCopyWith(DockerfileStateFailure value,
          $Res Function(DockerfileStateFailure) then) =
      _$DockerfileStateFailureCopyWithImpl<$Res>;
}

/// @nodoc
class _$DockerfileStateFailureCopyWithImpl<$Res>
    extends _$DockerfileStateCopyWithImpl<$Res>
    implements $DockerfileStateFailureCopyWith<$Res> {
  _$DockerfileStateFailureCopyWithImpl(DockerfileStateFailure _value,
      $Res Function(DockerfileStateFailure) _then)
      : super(_value, (v) => _then(v as DockerfileStateFailure));

  @override
  DockerfileStateFailure get _value => super._value as DockerfileStateFailure;
}

/// @nodoc

class _$DockerfileStateFailure implements DockerfileStateFailure {
  const _$DockerfileStateFailure();

  @override
  String toString() {
    return 'DockerfileState.failure()';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType && other is DockerfileStateFailure);
  }

  @override
  int get hashCode => runtimeType.hashCode;

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() initial,
    required TResult Function() loading,
    required TResult Function() failure,
    required TResult Function(String dockerfile) loaded,
  }) {
    return failure();
  }

  @override
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(String dockerfile)? loaded,
  }) {
    return failure?.call();
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(String dockerfile)? loaded,
    required TResult orElse(),
  }) {
    if (failure != null) {
      return failure();
    }
    return orElse();
  }

  @override
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(DockerfileStateInitial value) initial,
    required TResult Function(DockerfileStateLoading value) loading,
    required TResult Function(DockerfileStateFailure value) failure,
    required TResult Function(DockerfileStateLoaded value) loaded,
  }) {
    return failure(this);
  }

  @override
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(DockerfileStateInitial value)? initial,
    TResult Function(DockerfileStateLoading value)? loading,
    TResult Function(DockerfileStateFailure value)? failure,
    TResult Function(DockerfileStateLoaded value)? loaded,
  }) {
    return failure?.call(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(DockerfileStateInitial value)? initial,
    TResult Function(DockerfileStateLoading value)? loading,
    TResult Function(DockerfileStateFailure value)? failure,
    TResult Function(DockerfileStateLoaded value)? loaded,
    required TResult orElse(),
  }) {
    if (failure != null) {
      return failure(this);
    }
    return orElse();
  }
}

abstract class DockerfileStateFailure implements DockerfileState {
  const factory DockerfileStateFailure() = _$DockerfileStateFailure;
}

/// @nodoc
abstract class $DockerfileStateLoadedCopyWith<$Res> {
  factory $DockerfileStateLoadedCopyWith(DockerfileStateLoaded value,
          $Res Function(DockerfileStateLoaded) then) =
      _$DockerfileStateLoadedCopyWithImpl<$Res>;
  $Res call({String dockerfile});
}

/// @nodoc
class _$DockerfileStateLoadedCopyWithImpl<$Res>
    extends _$DockerfileStateCopyWithImpl<$Res>
    implements $DockerfileStateLoadedCopyWith<$Res> {
  _$DockerfileStateLoadedCopyWithImpl(
      DockerfileStateLoaded _value, $Res Function(DockerfileStateLoaded) _then)
      : super(_value, (v) => _then(v as DockerfileStateLoaded));

  @override
  DockerfileStateLoaded get _value => super._value as DockerfileStateLoaded;

  @override
  $Res call({
    Object? dockerfile = freezed,
  }) {
    return _then(DockerfileStateLoaded(
      dockerfile: dockerfile == freezed
          ? _value.dockerfile
          : dockerfile // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc

class _$DockerfileStateLoaded implements DockerfileStateLoaded {
  const _$DockerfileStateLoaded({required this.dockerfile});

  @override
  final String dockerfile;

  @override
  String toString() {
    return 'DockerfileState.loaded(dockerfile: $dockerfile)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is DockerfileStateLoaded &&
            (identical(other.dockerfile, dockerfile) ||
                other.dockerfile == dockerfile));
  }

  @override
  int get hashCode => Object.hash(runtimeType, dockerfile);

  @JsonKey(ignore: true)
  @override
  $DockerfileStateLoadedCopyWith<DockerfileStateLoaded> get copyWith =>
      _$DockerfileStateLoadedCopyWithImpl<DockerfileStateLoaded>(
          this, _$identity);

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() initial,
    required TResult Function() loading,
    required TResult Function() failure,
    required TResult Function(String dockerfile) loaded,
  }) {
    return loaded(dockerfile);
  }

  @override
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(String dockerfile)? loaded,
  }) {
    return loaded?.call(dockerfile);
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(String dockerfile)? loaded,
    required TResult orElse(),
  }) {
    if (loaded != null) {
      return loaded(dockerfile);
    }
    return orElse();
  }

  @override
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(DockerfileStateInitial value) initial,
    required TResult Function(DockerfileStateLoading value) loading,
    required TResult Function(DockerfileStateFailure value) failure,
    required TResult Function(DockerfileStateLoaded value) loaded,
  }) {
    return loaded(this);
  }

  @override
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(DockerfileStateInitial value)? initial,
    TResult Function(DockerfileStateLoading value)? loading,
    TResult Function(DockerfileStateFailure value)? failure,
    TResult Function(DockerfileStateLoaded value)? loaded,
  }) {
    return loaded?.call(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(DockerfileStateInitial value)? initial,
    TResult Function(DockerfileStateLoading value)? loading,
    TResult Function(DockerfileStateFailure value)? failure,
    TResult Function(DockerfileStateLoaded value)? loaded,
    required TResult orElse(),
  }) {
    if (loaded != null) {
      return loaded(this);
    }
    return orElse();
  }
}

abstract class DockerfileStateLoaded implements DockerfileState {
  const factory DockerfileStateLoaded({required String dockerfile}) =
      _$DockerfileStateLoaded;

  String get dockerfile;
  @JsonKey(ignore: true)
  $DockerfileStateLoadedCopyWith<DockerfileStateLoaded> get copyWith =>
      throw _privateConstructorUsedError;
}
