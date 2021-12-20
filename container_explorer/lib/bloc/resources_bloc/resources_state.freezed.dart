// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'resources_state.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

/// @nodoc
class _$ResourcesStateTearOff {
  const _$ResourcesStateTearOff();

  ResourcesStateInitial initial() {
    return const ResourcesStateInitial();
  }

  ResourcesStateLoading loading() {
    return const ResourcesStateLoading();
  }

  ResourcesStateFailure failure() {
    return const ResourcesStateFailure();
  }

  ResourcesStateLoaded loaded({required Map<String, dynamic> res}) {
    return ResourcesStateLoaded(
      res: res,
    );
  }
}

/// @nodoc
const $ResourcesState = _$ResourcesStateTearOff();

/// @nodoc
mixin _$ResourcesState {
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() initial,
    required TResult Function() loading,
    required TResult Function() failure,
    required TResult Function(Map<String, dynamic> res) loaded,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(Map<String, dynamic> res)? loaded,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(Map<String, dynamic> res)? loaded,
    required TResult orElse(),
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(ResourcesStateInitial value) initial,
    required TResult Function(ResourcesStateLoading value) loading,
    required TResult Function(ResourcesStateFailure value) failure,
    required TResult Function(ResourcesStateLoaded value) loaded,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(ResourcesStateInitial value)? initial,
    TResult Function(ResourcesStateLoading value)? loading,
    TResult Function(ResourcesStateFailure value)? failure,
    TResult Function(ResourcesStateLoaded value)? loaded,
  }) =>
      throw _privateConstructorUsedError;
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(ResourcesStateInitial value)? initial,
    TResult Function(ResourcesStateLoading value)? loading,
    TResult Function(ResourcesStateFailure value)? failure,
    TResult Function(ResourcesStateLoaded value)? loaded,
    required TResult orElse(),
  }) =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $ResourcesStateCopyWith<$Res> {
  factory $ResourcesStateCopyWith(
          ResourcesState value, $Res Function(ResourcesState) then) =
      _$ResourcesStateCopyWithImpl<$Res>;
}

/// @nodoc
class _$ResourcesStateCopyWithImpl<$Res>
    implements $ResourcesStateCopyWith<$Res> {
  _$ResourcesStateCopyWithImpl(this._value, this._then);

  final ResourcesState _value;
  // ignore: unused_field
  final $Res Function(ResourcesState) _then;
}

/// @nodoc
abstract class $ResourcesStateInitialCopyWith<$Res> {
  factory $ResourcesStateInitialCopyWith(ResourcesStateInitial value,
          $Res Function(ResourcesStateInitial) then) =
      _$ResourcesStateInitialCopyWithImpl<$Res>;
}

/// @nodoc
class _$ResourcesStateInitialCopyWithImpl<$Res>
    extends _$ResourcesStateCopyWithImpl<$Res>
    implements $ResourcesStateInitialCopyWith<$Res> {
  _$ResourcesStateInitialCopyWithImpl(
      ResourcesStateInitial _value, $Res Function(ResourcesStateInitial) _then)
      : super(_value, (v) => _then(v as ResourcesStateInitial));

  @override
  ResourcesStateInitial get _value => super._value as ResourcesStateInitial;
}

/// @nodoc

class _$ResourcesStateInitial implements ResourcesStateInitial {
  const _$ResourcesStateInitial();

  @override
  String toString() {
    return 'ResourcesState.initial()';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType && other is ResourcesStateInitial);
  }

  @override
  int get hashCode => runtimeType.hashCode;

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() initial,
    required TResult Function() loading,
    required TResult Function() failure,
    required TResult Function(Map<String, dynamic> res) loaded,
  }) {
    return initial();
  }

  @override
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(Map<String, dynamic> res)? loaded,
  }) {
    return initial?.call();
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(Map<String, dynamic> res)? loaded,
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
    required TResult Function(ResourcesStateInitial value) initial,
    required TResult Function(ResourcesStateLoading value) loading,
    required TResult Function(ResourcesStateFailure value) failure,
    required TResult Function(ResourcesStateLoaded value) loaded,
  }) {
    return initial(this);
  }

  @override
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(ResourcesStateInitial value)? initial,
    TResult Function(ResourcesStateLoading value)? loading,
    TResult Function(ResourcesStateFailure value)? failure,
    TResult Function(ResourcesStateLoaded value)? loaded,
  }) {
    return initial?.call(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(ResourcesStateInitial value)? initial,
    TResult Function(ResourcesStateLoading value)? loading,
    TResult Function(ResourcesStateFailure value)? failure,
    TResult Function(ResourcesStateLoaded value)? loaded,
    required TResult orElse(),
  }) {
    if (initial != null) {
      return initial(this);
    }
    return orElse();
  }
}

abstract class ResourcesStateInitial implements ResourcesState {
  const factory ResourcesStateInitial() = _$ResourcesStateInitial;
}

/// @nodoc
abstract class $ResourcesStateLoadingCopyWith<$Res> {
  factory $ResourcesStateLoadingCopyWith(ResourcesStateLoading value,
          $Res Function(ResourcesStateLoading) then) =
      _$ResourcesStateLoadingCopyWithImpl<$Res>;
}

/// @nodoc
class _$ResourcesStateLoadingCopyWithImpl<$Res>
    extends _$ResourcesStateCopyWithImpl<$Res>
    implements $ResourcesStateLoadingCopyWith<$Res> {
  _$ResourcesStateLoadingCopyWithImpl(
      ResourcesStateLoading _value, $Res Function(ResourcesStateLoading) _then)
      : super(_value, (v) => _then(v as ResourcesStateLoading));

  @override
  ResourcesStateLoading get _value => super._value as ResourcesStateLoading;
}

/// @nodoc

class _$ResourcesStateLoading implements ResourcesStateLoading {
  const _$ResourcesStateLoading();

  @override
  String toString() {
    return 'ResourcesState.loading()';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType && other is ResourcesStateLoading);
  }

  @override
  int get hashCode => runtimeType.hashCode;

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() initial,
    required TResult Function() loading,
    required TResult Function() failure,
    required TResult Function(Map<String, dynamic> res) loaded,
  }) {
    return loading();
  }

  @override
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(Map<String, dynamic> res)? loaded,
  }) {
    return loading?.call();
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(Map<String, dynamic> res)? loaded,
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
    required TResult Function(ResourcesStateInitial value) initial,
    required TResult Function(ResourcesStateLoading value) loading,
    required TResult Function(ResourcesStateFailure value) failure,
    required TResult Function(ResourcesStateLoaded value) loaded,
  }) {
    return loading(this);
  }

  @override
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(ResourcesStateInitial value)? initial,
    TResult Function(ResourcesStateLoading value)? loading,
    TResult Function(ResourcesStateFailure value)? failure,
    TResult Function(ResourcesStateLoaded value)? loaded,
  }) {
    return loading?.call(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(ResourcesStateInitial value)? initial,
    TResult Function(ResourcesStateLoading value)? loading,
    TResult Function(ResourcesStateFailure value)? failure,
    TResult Function(ResourcesStateLoaded value)? loaded,
    required TResult orElse(),
  }) {
    if (loading != null) {
      return loading(this);
    }
    return orElse();
  }
}

abstract class ResourcesStateLoading implements ResourcesState {
  const factory ResourcesStateLoading() = _$ResourcesStateLoading;
}

/// @nodoc
abstract class $ResourcesStateFailureCopyWith<$Res> {
  factory $ResourcesStateFailureCopyWith(ResourcesStateFailure value,
          $Res Function(ResourcesStateFailure) then) =
      _$ResourcesStateFailureCopyWithImpl<$Res>;
}

/// @nodoc
class _$ResourcesStateFailureCopyWithImpl<$Res>
    extends _$ResourcesStateCopyWithImpl<$Res>
    implements $ResourcesStateFailureCopyWith<$Res> {
  _$ResourcesStateFailureCopyWithImpl(
      ResourcesStateFailure _value, $Res Function(ResourcesStateFailure) _then)
      : super(_value, (v) => _then(v as ResourcesStateFailure));

  @override
  ResourcesStateFailure get _value => super._value as ResourcesStateFailure;
}

/// @nodoc

class _$ResourcesStateFailure implements ResourcesStateFailure {
  const _$ResourcesStateFailure();

  @override
  String toString() {
    return 'ResourcesState.failure()';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType && other is ResourcesStateFailure);
  }

  @override
  int get hashCode => runtimeType.hashCode;

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() initial,
    required TResult Function() loading,
    required TResult Function() failure,
    required TResult Function(Map<String, dynamic> res) loaded,
  }) {
    return failure();
  }

  @override
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(Map<String, dynamic> res)? loaded,
  }) {
    return failure?.call();
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(Map<String, dynamic> res)? loaded,
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
    required TResult Function(ResourcesStateInitial value) initial,
    required TResult Function(ResourcesStateLoading value) loading,
    required TResult Function(ResourcesStateFailure value) failure,
    required TResult Function(ResourcesStateLoaded value) loaded,
  }) {
    return failure(this);
  }

  @override
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(ResourcesStateInitial value)? initial,
    TResult Function(ResourcesStateLoading value)? loading,
    TResult Function(ResourcesStateFailure value)? failure,
    TResult Function(ResourcesStateLoaded value)? loaded,
  }) {
    return failure?.call(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(ResourcesStateInitial value)? initial,
    TResult Function(ResourcesStateLoading value)? loading,
    TResult Function(ResourcesStateFailure value)? failure,
    TResult Function(ResourcesStateLoaded value)? loaded,
    required TResult orElse(),
  }) {
    if (failure != null) {
      return failure(this);
    }
    return orElse();
  }
}

abstract class ResourcesStateFailure implements ResourcesState {
  const factory ResourcesStateFailure() = _$ResourcesStateFailure;
}

/// @nodoc
abstract class $ResourcesStateLoadedCopyWith<$Res> {
  factory $ResourcesStateLoadedCopyWith(ResourcesStateLoaded value,
          $Res Function(ResourcesStateLoaded) then) =
      _$ResourcesStateLoadedCopyWithImpl<$Res>;
  $Res call({Map<String, dynamic> res});
}

/// @nodoc
class _$ResourcesStateLoadedCopyWithImpl<$Res>
    extends _$ResourcesStateCopyWithImpl<$Res>
    implements $ResourcesStateLoadedCopyWith<$Res> {
  _$ResourcesStateLoadedCopyWithImpl(
      ResourcesStateLoaded _value, $Res Function(ResourcesStateLoaded) _then)
      : super(_value, (v) => _then(v as ResourcesStateLoaded));

  @override
  ResourcesStateLoaded get _value => super._value as ResourcesStateLoaded;

  @override
  $Res call({
    Object? res = freezed,
  }) {
    return _then(ResourcesStateLoaded(
      res: res == freezed
          ? _value.res
          : res // ignore: cast_nullable_to_non_nullable
              as Map<String, dynamic>,
    ));
  }
}

/// @nodoc

class _$ResourcesStateLoaded implements ResourcesStateLoaded {
  const _$ResourcesStateLoaded({required this.res});

  @override
  final Map<String, dynamic> res;

  @override
  String toString() {
    return 'ResourcesState.loaded(res: $res)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is ResourcesStateLoaded &&
            const DeepCollectionEquality().equals(other.res, res));
  }

  @override
  int get hashCode =>
      Object.hash(runtimeType, const DeepCollectionEquality().hash(res));

  @JsonKey(ignore: true)
  @override
  $ResourcesStateLoadedCopyWith<ResourcesStateLoaded> get copyWith =>
      _$ResourcesStateLoadedCopyWithImpl<ResourcesStateLoaded>(
          this, _$identity);

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object?>({
    required TResult Function() initial,
    required TResult Function() loading,
    required TResult Function() failure,
    required TResult Function(Map<String, dynamic> res) loaded,
  }) {
    return loaded(res);
  }

  @override
  @optionalTypeArgs
  TResult? whenOrNull<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(Map<String, dynamic> res)? loaded,
  }) {
    return loaded?.call(res);
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object?>({
    TResult Function()? initial,
    TResult Function()? loading,
    TResult Function()? failure,
    TResult Function(Map<String, dynamic> res)? loaded,
    required TResult orElse(),
  }) {
    if (loaded != null) {
      return loaded(res);
    }
    return orElse();
  }

  @override
  @optionalTypeArgs
  TResult map<TResult extends Object?>({
    required TResult Function(ResourcesStateInitial value) initial,
    required TResult Function(ResourcesStateLoading value) loading,
    required TResult Function(ResourcesStateFailure value) failure,
    required TResult Function(ResourcesStateLoaded value) loaded,
  }) {
    return loaded(this);
  }

  @override
  @optionalTypeArgs
  TResult? mapOrNull<TResult extends Object?>({
    TResult Function(ResourcesStateInitial value)? initial,
    TResult Function(ResourcesStateLoading value)? loading,
    TResult Function(ResourcesStateFailure value)? failure,
    TResult Function(ResourcesStateLoaded value)? loaded,
  }) {
    return loaded?.call(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object?>({
    TResult Function(ResourcesStateInitial value)? initial,
    TResult Function(ResourcesStateLoading value)? loading,
    TResult Function(ResourcesStateFailure value)? failure,
    TResult Function(ResourcesStateLoaded value)? loaded,
    required TResult orElse(),
  }) {
    if (loaded != null) {
      return loaded(this);
    }
    return orElse();
  }
}

abstract class ResourcesStateLoaded implements ResourcesState {
  const factory ResourcesStateLoaded({required Map<String, dynamic> res}) =
      _$ResourcesStateLoaded;

  Map<String, dynamic> get res;
  @JsonKey(ignore: true)
  $ResourcesStateLoadedCopyWith<ResourcesStateLoaded> get copyWith =>
      throw _privateConstructorUsedError;
}
