import 'dart:async';
import 'dart:convert';
import 'dart:io';

import 'package:flutter/services.dart';
import 'package:okru_oauth/ok_auth_result.dart';

class OkruOauth {
  static const MethodChannel _channel = MethodChannel('okru_oauth');

  static Future<void> init({required String appId, required String appKey}) async {
    await _channel.invokeMethod('init', {'appID': appId, 'appKey': appKey});
  }

  static Future<OkAuthResult> login() async {
    final result = await _channel.invokeMethod('auth');
    return Platform.isAndroid ? OkAuthResult.fromRawJson(result) : OkAuthResult.fromList(result);
  }
}
