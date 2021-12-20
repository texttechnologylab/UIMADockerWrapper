// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'parameter.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

Parameter _$ParameterFromJson(Map<String, dynamic> json) {
  return _Parameter.fromJson(json);
}

/// @nodoc
class _$ParameterTearOff {
  const _$ParameterTearOff();

  _Parameter call(
      {required String name,
      required String description,
      required String type,
      required bool multivalued,
      required bool mandatory,
      required dynamic value}) {
    return _Parameter(
      name: name,
      description: description,
      type: type,
      multivalued: multivalued,
      mandatory: mandatory,
      value: value,
    );
  }

  Parameter fromJson(Map<String, Object?> json) {
    return Parameter.fromJson(json);
  }
}

/// @nodoc
const $Parameter = _$ParameterTearOff();

/// @nodoc
mixin _$Parameter {
  String get name => throw _privateConstructorUsedError;
  String get description => throw _privateConstructorUsedError;
  String get type => throw _privateConstructorUsedError;
  bool get multivalued => throw _privateConstructorUsedError;
  bool get mandatory => throw _privateConstructorUsedError;
  dynamic get value => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $ParameterCopyWith<Parameter> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $ParameterCopyWith<$Res> {
  factory $ParameterCopyWith(Parameter value, $Res Function(Parameter) then) =
      _$ParameterCopyWithImpl<$Res>;
  $Res call(
      {String name,
      String description,
      String type,
      bool multivalued,
      bool mandatory,
      dynamic value});
}

/// @nodoc
class _$ParameterCopyWithImpl<$Res> implements $ParameterCopyWith<$Res> {
  _$ParameterCopyWithImpl(this._value, this._then);

  final Parameter _value;
  // ignore: unused_field
  final $Res Function(Parameter) _then;

  @override
  $Res call({
    Object? name = freezed,
    Object? description = freezed,
    Object? type = freezed,
    Object? multivalued = freezed,
    Object? mandatory = freezed,
    Object? value = freezed,
  }) {
    return _then(_value.copyWith(
      name: name == freezed
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
      description: description == freezed
          ? _value.description
          : description // ignore: cast_nullable_to_non_nullable
              as String,
      type: type == freezed
          ? _value.type
          : type // ignore: cast_nullable_to_non_nullable
              as String,
      multivalued: multivalued == freezed
          ? _value.multivalued
          : multivalued // ignore: cast_nullable_to_non_nullable
              as bool,
      mandatory: mandatory == freezed
          ? _value.mandatory
          : mandatory // ignore: cast_nullable_to_non_nullable
              as bool,
      value: value == freezed
          ? _value.value
          : value // ignore: cast_nullable_to_non_nullable
              as dynamic,
    ));
  }
}

/// @nodoc
abstract class _$ParameterCopyWith<$Res> implements $ParameterCopyWith<$Res> {
  factory _$ParameterCopyWith(
          _Parameter value, $Res Function(_Parameter) then) =
      __$ParameterCopyWithImpl<$Res>;
  @override
  $Res call(
      {String name,
      String description,
      String type,
      bool multivalued,
      bool mandatory,
      dynamic value});
}

/// @nodoc
class __$ParameterCopyWithImpl<$Res> extends _$ParameterCopyWithImpl<$Res>
    implements _$ParameterCopyWith<$Res> {
  __$ParameterCopyWithImpl(_Parameter _value, $Res Function(_Parameter) _then)
      : super(_value, (v) => _then(v as _Parameter));

  @override
  _Parameter get _value => super._value as _Parameter;

  @override
  $Res call({
    Object? name = freezed,
    Object? description = freezed,
    Object? type = freezed,
    Object? multivalued = freezed,
    Object? mandatory = freezed,
    Object? value = freezed,
  }) {
    return _then(_Parameter(
      name: name == freezed
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
      description: description == freezed
          ? _value.description
          : description // ignore: cast_nullable_to_non_nullable
              as String,
      type: type == freezed
          ? _value.type
          : type // ignore: cast_nullable_to_non_nullable
              as String,
      multivalued: multivalued == freezed
          ? _value.multivalued
          : multivalued // ignore: cast_nullable_to_non_nullable
              as bool,
      mandatory: mandatory == freezed
          ? _value.mandatory
          : mandatory // ignore: cast_nullable_to_non_nullable
              as bool,
      value: value == freezed
          ? _value.value
          : value // ignore: cast_nullable_to_non_nullable
              as dynamic,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_Parameter extends _Parameter {
  const _$_Parameter(
      {required this.name,
      required this.description,
      required this.type,
      required this.multivalued,
      required this.mandatory,
      required this.value})
      : super._();

  factory _$_Parameter.fromJson(Map<String, dynamic> json) =>
      _$$_ParameterFromJson(json);

  @override
  final String name;
  @override
  final String description;
  @override
  final String type;
  @override
  final bool multivalued;
  @override
  final bool mandatory;
  @override
  final dynamic value;

  @override
  String toString() {
    return 'Parameter(name: $name, description: $description, type: $type, multivalued: $multivalued, mandatory: $mandatory, value: $value)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _Parameter &&
            (identical(other.name, name) || other.name == name) &&
            (identical(other.description, description) ||
                other.description == description) &&
            (identical(other.type, type) || other.type == type) &&
            (identical(other.multivalued, multivalued) ||
                other.multivalued == multivalued) &&
            (identical(other.mandatory, mandatory) ||
                other.mandatory == mandatory) &&
            const DeepCollectionEquality().equals(other.value, value));
  }

  @override
  int get hashCode => Object.hash(runtimeType, name, description, type,
      multivalued, mandatory, const DeepCollectionEquality().hash(value));

  @JsonKey(ignore: true)
  @override
  _$ParameterCopyWith<_Parameter> get copyWith =>
      __$ParameterCopyWithImpl<_Parameter>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_ParameterToJson(this);
  }
}

abstract class _Parameter extends Parameter {
  const factory _Parameter(
      {required String name,
      required String description,
      required String type,
      required bool multivalued,
      required bool mandatory,
      required dynamic value}) = _$_Parameter;
  const _Parameter._() : super._();

  factory _Parameter.fromJson(Map<String, dynamic> json) =
      _$_Parameter.fromJson;

  @override
  String get name;
  @override
  String get description;
  @override
  String get type;
  @override
  bool get multivalued;
  @override
  bool get mandatory;
  @override
  dynamic get value;
  @override
  @JsonKey(ignore: true)
  _$ParameterCopyWith<_Parameter> get copyWith =>
      throw _privateConstructorUsedError;
}
