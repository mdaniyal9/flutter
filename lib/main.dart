import 'package:flutter/material.dart';

void main() {
  runApp(MaterialApp(
    home: Home(),
  ));
}

class Home extends StatelessWidget {
  @override
  Widget build(context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('my first app',),
        centerTitle: true,
        backgroundColor: Colors.red[600],
      ),
      body: Center(
        child: FlatButton(
          onPressed: () {
            print('you clicked me');
          },
          child: Text('click me'),
          color: Colors.lightBlue,
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {  },
        child: Text('click'),
        backgroundColor: Colors.red[600],
      ),
    );
  }
}


