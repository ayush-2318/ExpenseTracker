import { View, Text,StyleSheet, TextInput } from 'react-native'
import React, { useEffect, useState } from 'react'
import CustomBox from '../Components/CustomBox'
//import { StyleSheet } from 'react-native-css-interop'
import CustomText from '../Components/CustomText'
import { Button } from '@gluestack-ui/themed'
import AsyncStorage from '@react-native-async-storage/async-storage';
import Home from './Home'



const Login = ({navigation}) => {
  const [userName,setUserName]=useState('');
  const [password,setPassword]=useState('');
  const [loggedIn,setLoggedIn]=useState(true);
  

  const goToSignUp =()=>{
    navigation.navigate('SignUp',{name:'SignUp'})
  }

  const isLoggedIn = async()=>{
    const accessToken=await AsyncStorage.getItem('accessToken')
    console.log(accessToken);
    const response= await fetch('http://192.168.82.6:9892/ping',{
      method :'GET',
      headers: {
        Accept:'application/json',
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + accessToken,
        'X-Requested-With': 'XMLHttpRequest'
      }
    })
    return response.ok;
  }
    
   

  const gotoHomeWithLogIn=async ()=>{
    const response=await fetch('http://192.168.82.6:9892/auth/v1/login',{
      method:'POST',
      headers:{
        Accept:'application/json',
        'Content-Type':'application/json',
        'X-Requested-With':'XMLHttpRequest'
      },
      body: JSON.stringify({
        userName:userName,
        password: password
      })
    });

    if(response.ok){
      const data=await response.json();
      await AsyncStorage.setItem('refreshToken',data['token'])
      await AsyncStorage.setItem('accessToken',data['accessToken'])
      navigation.navigate('Home',{name:Home})
    }

  }

  /*
  const refreshToken=async()=>{
    const refreshToken=await AsyncStorage.getItem('refreshToken')
    const response =  await fetch('http://192.168.82.6:9892/auth/v1/refreshToken',{
      method :'GET',
      headers: {
        Accept:'application/json',
        'Content-Type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest'
      },
      body: JSON.stringify({
        token :refreshToken
      }),

     
    })
    if(response.ok){
      const data= await response.json()
      await AsyncStorage.setItem('accessToken', data['accessToken'])
      await AsyncStorage.setItem('refreshToken',data['token'])
      
    }
    return response.ok
    
  }
    */

const refreshToken = async () => {
  const refreshToken = await AsyncStorage.getItem('refreshToken');
  const response = await fetch('http://192.168.82.6:9892/auth/v1/refreshToken', {
    method: 'POST', // Not GET!
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest',
    },
    body: JSON.stringify({ token: refreshToken }),
  });

  if (response.ok) {
    const data = await response.json();
    await AsyncStorage.setItem('accessToken', data['accessToken']);
    await AsyncStorage.setItem('refreshToken', data['token']);
    return true;
  } else {
    // Explicitly remove invalid tokens
    await AsyncStorage.removeItem('accessToken');
    await AsyncStorage.removeItem('refreshToken');
    return false;
  }
};

/*
  useEffect(()=>{
    const handleLogin= async() =>{
      const loggedIn= await isLoggedIn();
      setLoggedIn(loggedIn)
      if(loggedIn){
        navigation.navigate('Home',{name : 'Home'})
      }else{
        const refreshed=await refreshToken()
        setLoggedIn(refreshed)
        if(refreshed){
          navigation.navigate('Home',{name:'Home'})
        }
      }
    }
    handleLogin()
  },[])
  */
 useEffect(() => {
  const handleLogin = async () => {
    try {
      const loggedIn = await isLoggedIn();
      if (loggedIn) {
        navigation.navigate('Home', { name: 'Home' });
        return;
      }

      const refreshed = await refreshToken();
      if (refreshed) {
        navigation.navigate('Home', { name: 'Home' });
        return;
      }

      // If both fail, stay on login and clean up
      await AsyncStorage.removeItem('accessToken');
      await AsyncStorage.removeItem('refreshToken');
      setLoggedIn(false);

    } catch (err) {
      console.error('Login check failed', err);
      setLoggedIn(false);
    }
  };

  handleLogin();
}, []);


  return (
    <View style={styles.loginContainer}>
     <CustomBox style={loginBox}>
      <CustomText style={styles.heading}>Login</CustomText>
      <TextInput
            placeholder='User Name'
            value={userName}
            onChangeText={text => setUserName(text)}
            style={styles.textInput}
            placeholderTextColor="#888"/>
        
        <TextInput
            placeholder='Password'
            value={password}
            onChangeText={text => setPassword(text)}
            style={styles.textInput}
            placeholderTextColor="#888"
            secureTextEntry/>
     </CustomBox>
     <Button onPressIn={() =>gotoHomeWithLogIn()} style={styles.button}>
      <CustomBox style={buttonBox}>
        <CustomText style={{textAlign:'center'}}>LogIn</CustomText>
      </CustomBox>
     </Button>
     <Button onPressIn={() => goToSignUp()} style={styles.button}>
      <CustomBox style={buttonBox}>
        <CustomText style={{textAlign:'center'}}>SignUp</CustomText>
      </CustomBox>
     </Button>
    </View>
  )
}

const loginBox = {
      mainBox:{
        backgroundColor: '#fff',
        borderColor: 'black',
        borderWidth: 1,
        borderRadius: 10,
        padding: 20,
      },
      shadowBox:{
        backgroundColor:'gray',
        borderRadius: 10,
      },

}

const buttonBox ={
  mainBox:{
    backgroundColor: '#fff',
    borderColor: 'black',
    borderWidth: 1,
    borderRadius: 10,
    padding: 10,
  },
  shadowBox:{
    backgroundColor:'gray',
    borderRadius: 10,
  },
}

const styles= StyleSheet.create({
  heading:{
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom:20
  },
  textInput:{
    backgroundColor:'#f0f0f0',
    borderRadius:10,
    padding: 10,
    marginBottom: 15,
    width:'100%',
    color:'black'
  },
  loginContainer:{
    flex:1,
    justifyContent:'center',
    alignItems:'center',
    padding:20
  },
  button:{
    marginTop:20,
    width:'30%'
  }
})

export default Login