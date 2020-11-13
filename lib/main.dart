import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_app/pages/choose_location.dart';
import 'package:flutter_app/pages/home.dart';
import 'package:flutter_app/pages/loading.dart';
void main() {
  runApp(MaterialApp(
    // initialRoute: '/',
    // routes: {
    //   '/': (context) => Loading(),
    //   '/home': (context) => Home(),
    //   '/location': (context) => ChooseLocation(),
    // },
    home: _MyHomePageState(),
  ));
}

class _MyHomePageState extends StatefulWidget {
  @override
  __MyHomePageStateState createState() => __MyHomePageStateState();
}

class __MyHomePageStateState extends State<_MyHomePageState> {
  static const platform = const MethodChannel('playground');
  String response = '';


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('device library'),
        centerTitle: true,
        backgroundColor: Colors.blue,
      ),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: <Widget>[
          FlatButton(
            color: Colors.cyan,
            onPressed: () async {
              String result = await platform.invokeMethod('check');
              setState(() {
                response = result;
              });
            },
            child: Text('button 1'),
          ),
          Text(response),
          FlatButton(
            color: Colors.cyan,
            onPressed: (){},
            child: Text('button 2'),
          ),
          FlatButton(
            color: Colors.cyan,
            onPressed: (){},
            child: Text('button 3'),
          ),
          FlatButton(
            color: Colors.cyan,
            onPressed: (){},
            child: Text('button 4'),
          ),
          FlatButton(
            color: Colors.cyan,
            onPressed: (){},
            child: Text('button 5'),
          ),
          FlatButton(
            color: Colors.cyan,
            onPressed: (){},
            child: Text('button 6'),
          ),
        ],
      ),
    );
  }
}



