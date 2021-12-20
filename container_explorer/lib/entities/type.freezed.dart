// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'type.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more informations: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

UIMAType _$UIMATypeFromJson(Map<String, dynamic> json) {
  return _UIMAType.fromJson(json);
}

/// @nodoc
class _$UIMATypeTearOff {
  const _$UIMATypeTearOff();

  _UIMAType call(
      {required String name,
      required String? description,
      required List<UIMAType> children}) {
    return _UIMAType(
      name: name,
      description: description,
      children: children,
    );
  }

  UIMAType fromJson(Map<String, Object?> json) {
    return UIMAType.fromJson(json);
  }
}

/// @nodoc
const $UIMAType = _$UIMATypeTearOff();

/// @nodoc
mixin _$UIMAType {
  String get name => throw _privateConstructorUsedError;
  String? get description => throw _privateConstructorUsedError;
  List<UIMAType> get children => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $UIMATypeCopyWith<UIMAType> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $UIMATypeCopyWith<$Res> {
  factory $UIMATypeCopyWith(UIMAType value, $Res Function(UIMAType) then) =
      _$UIMATypeCopyWithImpl<$Res>;
  $Res call({String name, String? description, List<UIMAType> children});
}

/// @nodoc
class _$UIMATypeCopyWithImpl<$Res> implements $UIMATypeCopyWith<$Res> {
  _$UIMATypeCopyWithImpl(this._value, this._then);

  final UIMAType _value;
  // ignore: unused_field
  final $Res Function(UIMAType) _then;

  @override
  $Res call({
    Object? name = freezed,
    Object? description = freezed,
    Object? children = freezed,
  }) {
    return _then(_value.copyWith(
      name: name == freezed
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
      description: description == freezed
          ? _value.description
          : description // ignore: cast_nullable_to_non_nullable
              as String?,
      children: children == freezed
          ? _value.children
          : children // ignore: cast_nullable_to_non_nullable
              as List<UIMAType>,
    ));
  }
}

/// @nodoc
abstract class _$UIMATypeCopyWith<$Res> implements $UIMATypeCopyWith<$Res> {
  factory _$UIMATypeCopyWith(_UIMAType value, $Res Function(_UIMAType) then) =
      __$UIMATypeCopyWithImpl<$Res>;
  @override
  $Res call({String name, String? description, List<UIMAType> children});
}

/// @nodoc
class __$UIMATypeCopyWithImpl<$Res> extends _$UIMATypeCopyWithImpl<$Res>
    implements _$UIMATypeCopyWith<$Res> {
  __$UIMATypeCopyWithImpl(_UIMAType _value, $Res Function(_UIMAType) _then)
      : super(_value, (v) => _then(v as _UIMAType));

  @override
  _UIMAType get _value => super._value as _UIMAType;

  @override
  $Res call({
    Object? name = freezed,
    Object? description = freezed,
    Object? children = freezed,
  }) {
    return _then(_UIMAType(
      name: name == freezed
          ? _value.name
          : name // ignore: cast_nullable_to_non_nullable
              as String,
      description: description == freezed
          ? _value.description
          : description // ignore: cast_nullable_to_non_nullable
              as String?,
      children: children == freezed
          ? _value.children
          : children // ignore: cast_nullable_to_non_nullable
              as List<UIMAType>,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_UIMAType extends _UIMAType {
  const _$_UIMAType(
      {required this.name, required this.description, required this.children})
      : super._();

  factory _$_UIMAType.fromJson(Map<String, dynamic> json) =>
      _$$_UIMATypeFromJson(json);

  @override
  final String name;
  @override
  final String? description;
  @override
  final List<UIMAType> children;

  @override
  String toString() {
    return 'UIMAType(name: $name, description: $description, children: $children)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _UIMAType &&
            (identical(other.name, name) || other.name == name) &&
            (identical(other.description, description) ||
                other.description == description) &&
            const DeepCollectionEquality().equals(other.children, children));
  }

  @override
  int get hashCode => Object.hash(runtimeType, name, description,
      const DeepCollectionEquality().hash(children));

  @JsonKey(ignore: true)
  @override
  _$UIMATypeCopyWith<_UIMAType> get copyWith =>
      __$UIMATypeCopyWithImpl<_UIMAType>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_UIMATypeToJson(this);
  }
}

abstract class _UIMAType extends UIMAType {
  const factory _UIMAType(
      {required String name,
      required String? description,
      required List<UIMAType> children}) = _$_UIMAType;
  const _UIMAType._() : super._();

  factory _UIMAType.fromJson(Map<String, dynamic> json) = _$_UIMAType.fromJson;

  @override
  String get name;
  @override
  String? get description;
  @override
  List<UIMAType> get children;
  @override
  @JsonKey(ignore: true)
  _$UIMATypeCopyWith<_UIMAType> get copyWith =>
      throw _privateConstructorUsedError;
}
