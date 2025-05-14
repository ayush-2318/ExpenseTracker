/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React from 'react';
import type {PropsWithChildren} from 'react';
import {SafeAreaView,} from 'react-native';
import{createNativeStackNavigator} from '@react-navigation/native-stack';
import {
  Colors,
  DebugInstructions,
  Header,
  LearnMoreLinks,
  ReloadInstructions,
} from 'react-native/Libraries/NewAppScreen';
import { NavigationContainer } from '@react-navigation/native';
import Login from './src/app/Pages/Login';

import Home from './src/app/Pages/Home';
import SignUp from './src/app/Pages/SignUp';





function App(): React.JSX.Element {
  const Stack=createNativeStackNavigator()
  return (
    <NavigationContainer>
    <Stack.Navigator>
      <Stack.Screen name='Login' component={Login}/>
      <Stack.Screen name='SignUp' component={SignUp}/>
      <Stack.Screen name='Home' component={Home}/>
    </Stack.Navigator>
  </NavigationContainer>
  );
}



export default App;
