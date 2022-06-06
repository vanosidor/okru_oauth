import 'dart:convert';

class OkAuthResult {
  final String accessToken, sessionSecretKey;
  final int? expiresIn;

  OkAuthResult(this.accessToken, this.sessionSecretKey, [this.expiresIn]);

  factory OkAuthResult.fromRawJson(String raw) {
    final Map<String, dynamic> json = jsonDecode(raw);
    return OkAuthResult(json['access_token'], json['session_secret_key'], json['expires_in']);
  }

  factory OkAuthResult.fromList(List<Object?> list) {
    return OkAuthResult(list[0] as String, list[1] as String);
  }

  @override
  String toString() {
    return 'OkAuthResult {\n    access token: $accessToken\n    session secret key: $sessionSecretKey\n    expires in: $expiresIn\n}';
  }
}
