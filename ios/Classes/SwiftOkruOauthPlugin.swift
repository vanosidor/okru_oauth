import Flutter
import UIKit

public class SwiftOkruOauthPlugin: NSObject, FlutterPlugin {
    
    var settings: OKSDKInitSettings = OKSDKInitSettings()
    
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "okru_oauth", binaryMessenger: registrar.messenger())
    let instance = SwiftOkruOauthPlugin()
    let viewController = UIApplication.shared.windows.first!.rootViewController
      instance.settings.controllerHandler = {
          return viewController
      }
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
      switch call.method {
      case "init":
          settings.appId = (call.arguments as! Dictionary<String, String>)["appID"]
          settings.appKey = (call.arguments as! Dictionary<String, String>)["appKey"]
          OKSDK.initWith(settings)
          result(nil)
      case "auth":
          OKSDK.authorize(withPermissions: ["VALUABLE_ACCESS"], success: { data in
              result(data)
          }, error: { error in result(error)})
      default:
          result(nil)
          
      }
  }
}
