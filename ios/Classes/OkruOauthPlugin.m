#import "OkruOauthPlugin.h"
#if __has_include(<okru_oauth/okru_oauth-Swift.h>)
#import <okru_oauth/okru_oauth-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "okru_oauth-Swift.h"
#endif

@implementation OkruOauthPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftOkruOauthPlugin registerWithRegistrar:registrar];
}
@end
