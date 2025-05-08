import { View, Text,StyleSheet, TextInput } from 'react-native'
import React, { useState } from 'react'
import CustomBox from '../Components/CustomBox'
//import { StyleSheet } from 'react-native-css-interop'
import CustomText from '../Components/CustomText'
import { Button } from '@gluestack-ui/themed'

const Login = ({navigation}) => {
  const [userName,setUserName]=useState('');
  const [password,setPassword]=useState('');

  const goToSignUp =()=>{
    navigation.navigate('SignUp',{name:'SignUp'})
  }
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
     <Button onPressIn={() => {}} style={styles.button}>
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