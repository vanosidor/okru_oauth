# okru_oauth

okru auth library

## Getting Started

### Android setup 

add 
```xml
<activity
            android:name="ru.ok.android.sdk.OkAuthActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.VIEW" />

                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />

                <data
                    android:host="ok{* Your OK app id here *}"
                    android:scheme="okauth"/>
            </intent-filter>
        </activity>
```
to Manifest 


### IOs setup 

add 
```
 <key>NSAppTransportSecurity</key>
    <dict>
        <key>NSAllowsArbitraryLoads</key>
        <true/>
    </dict>
```
to Info.plist
also add 
```
<string>ok{* Your OK app id here *}</string>
```
to list of supported urls schemes in info.plist

then add

```swift 
import okru_oauth
```
to top of appdelegate file 
and 
```swift
override func application(_ app: UIApplication,
                              open url: URL,
                           options: [UIApplication.OpenURLOptionsKey : Any] = [:]) -> Bool {
        OKSDK.open(url)
        return true
    }
```
inside AppDelegate class in same file

# Example
to use example, you need to replace app id and app key inside both native parts(manifest and plist) and main.dart of example 
