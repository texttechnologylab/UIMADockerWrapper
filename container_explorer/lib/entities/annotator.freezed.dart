// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'annotator.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

Annotator _$AnnotatorFromJson(Map<String, dynamic> json) {
  return _Annotator.fromJson(json);
}

/// @nodoc
class _$AnnotatorTearOff {
  const _$AnnotatorTearOff();

  _Annotator call(
      {required String name,
      required List<Parameter> parameters,
      required List<UnlistedParameter> unlisted_parameters}) {
    return _Annotator(
      name: name,
      parameters: parameters,
      unlisted_parameters: unlisted_parameters,
    );
  }

  Annotator fromJson(Map<String, Object?> json) {
    return Annotator.fromJson(json);
  }
}

/// @nodoc
const $Annotator = _$AnnotatorTearOff();

/// @nodoc
mixin _$Annotator {
  String get name => throw _privateConstructorUsedError;
  List<Parameter> get parameters => throw _privateConstructorUsedError;
  List<UnlistedParameter> get unlisted_parameters =>
      throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $AnnotatorCopyWith<Annotator> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $AnnotatorCopyWith<$Res> {
  factory $AnnotatorCopyWith(Annotator value, $Res Function(Annotator) then) =
      _$AnnotatorCopyWithImpl<$Res>;
  $Res call(
      {String name,
      List<Parameter> parameters,
      List<UnlistedParameter> unlisted_parameters});
}

/// @nodoc
class _$AnnotatorCopyWithImpl<$Res> implements $AnnotatorCopyWith<$Res> {
  _$AnnotatorCopyWithImpl(this._value, this._then);

  final Annotator _value;
  // ignore: unused_field
  final $Res Function(Annotator) _then;

  @override
  $Res call({
    Object? name = freezed,
    Object? parameters = freezed,
    Object? unlisted_parameters = freezed,
  }) {
    return _then(_value.copyWith(
      name: name == freezed
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
      parameters: parameters == freezed
          ? _value.parameters
          : parameters // ignore: cast_nullable_to_non_nullable
              as List<Parameter>,
      unlisted_parameters: unlisted_parameters == freezed
          ? _value.unlisted_parameters
          : unlisted_parameters // ignore: cast_nullable_to_non_nullable
              as List<UnlistedParameter>,
    ));
  }
}

/// @nodoc
abstract class _$AnnotatorCopyWith<$Res> implements $AnnotatorCopyWith<$Res> {
  factory _$AnnotatorCopyWith(
          _Annotator value, $Res Function(_Annotator) then) =
      __$AnnotatorCopyWithImpl<$Res>;
  @override
  $Res call(
      {String name,
      List<Parameter> parameters,
      List<UnlistedParameter> unlisted_parameters});
}

/// @nodoc
class __$AnnotatorCopyWithImpl<$Res> extends _$AnnotatorCopyWithImpl<$Res>
    implements _$AnnotatorCopyWith<$Res> {
  __$AnnotatorCopyWithImpl(_Annotator _value, $Res Function(_Annotator) _then)
      : super(_value, (v) => _then(v as _Annotator));

  @override
  _Annotator get _value => super._value as _Annotator;

  @override
  $Res call({
    Object? name = freezed,
    Object? parameters = freezed,
    Object? unlisted_parameters = freezed,
  }) {
    return _then(_Annotator(
      name: name == freezed
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
      parameters: parameters == freezed
          ? _value.parameters
          : parameters // ignore: cast_nullable_to_non_nullable
              as List<Parameter>,
      unlisted_parameters: unlisted_parameters == freezed
          ? _value.unlisted_parameters
          : unlisted_parameters // ignore: cast_nullable_to_non_nullable
              as List<UnlistedParameter>,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_Annotator extends _Annotator {
  const _$_Annotator(
      {required this.name,
      required this.parameters,
      required this.unlisted_parameters})
      : super._();

  factory _$_Annotator.fromJson(Map<String, dynamic> json) =>
      _$$_AnnotatorFromJson(json);

  @override
  final String name;
  @override
  final List<Parameter> parameters;
  @override
  final List<UnlistedParameter> unlisted_parameters;

  @override
  String toString() {
    return 'Annotator(name: $name, parameters: $parameters, unlisted_parameters: $unlisted_parameters)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _Annotator &&
            (identical(other.name, name) || other.name == name) &&
            const DeepCollectionEquality()
                .equals(other.parameters, parameters) &&
            const DeepCollectionEquality()
                .equals(other.unlisted_parameters, unlisted_parameters));
  }

  @override
  int get hashCode => Object.hash(
      runtimeType,
      name,
      const DeepCollectionEquality().hash(parameters),
      const DeepCollectionEquality().hash(unlisted_parameters));

  @JsonKey(ignore: true)
  @override
  _$AnnotatorCopyWith<_Annotator> get copyWith =>
      __$AnnotatorCopyWithImpl<_Annotator>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_AnnotatorToJson(this);
  }
}

abstract class _Annotator extends Annotator {
  const factory _Annotator(
      {required String name,
      required List<Parameter> parameters,
      required List<UnlistedParameter> unlisted_parameters}) = _$_Annotator;
  const _Annotator._() : super._();

  factory _Annotator.fromJson(Map<String, dynamic> json) =
      _$_Annotator.fromJson;

  @override
  String get name;
  @override
  List<Parameter> get parameters;
  @override
  List<UnlistedParameter> get unlisted_parameters;
  @override
  @JsonKey(ignore: true)
  _$AnnotatorCopyWith<_Annotator> get copyWith =>
      throw _privateConstructorUsedError;
}
