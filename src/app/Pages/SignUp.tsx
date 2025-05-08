import {  StyleSheet, Text, TextInput, View } from 'react-native'
import React, { useState } from 'react'
import CustomBox from '../Components/CustomBox';
import CustomText from '../Components/CustomText';
import { Button } from '@gluestack-ui/themed'

const SignUp =({navigation})=> {
    const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [userName, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');

  const goToLogInWithoutValidation=()=>{
    navigation.navigate('Login',{name:'Login'})
  }

  return (
    <View style={styles.signupContainer}>
    <CustomBox style={signUpBox}>
      <CustomText style={styles.heading}>Sign Up</CustomText>
      <TextInput
        placeholder="First Name"
        value={firstName}
        onChangeText={text => setFirstName(text)}
        style={styles.textInput}
        placeholderTextColor="#888"
      />
      <TextInput
        placeholder="Last Name"
        value={lastName}
        onChangeText={text => setLastName(text)}
        style={styles.textInput}
        placeholderTextColor="#888"
      />
            <TextInput
        placeholder="User Name"
        value={userName}
        onChangeText={text => setUserName(text)}
        style={styles.textInput}
        placeholderTextColor="#888"
      />
      <TextInput
        placeholder="Email"
        value={email}
        onChangeText={text => setEmail(text)}
        style={styles.textInput}
        placeholderTextColor="#888"
        keyboardType="email-address"
      />
      <TextInput
        placeholder="Password"
        value={password}
        onChangeText={text => setPassword(text)}
        style={styles.textInput}
        placeholderTextColor="#888"
        secureTextEntry
      />
      <TextInput
        placeholder="Phone Number"
        value={phoneNumber}
        onChangeText={text => setPhoneNumber(text)}
        style={styles.textInput}
        placeholderTextColor="#888"
        keyboardType="phone-pad"
      />
    </CustomBox>
    <Button onPressIn={() => {}} style={styles.button}>
        <CustomBox style={buttonBox}>
            <CustomText style={{textAlign: 'center'}}>Sign Up</CustomText>
        </CustomBox>
      </Button>
      <Button onPressIn={() => goToLogInWithoutValidation()} style={styles.button}>
        <CustomBox style={buttonBox}>
            <CustomText style={{textAlign: 'center'}}>Login</CustomText>
        </CustomBox>
      </Button>

  </View>
  )
}

const styles = StyleSheet.create({
    signupContainer: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        padding: 20,
      },
      button: {
        marginTop: 20,
        width: "30%",
      },
      heading: {
        fontSize: 24,
        fontWeight: 'bold',
        marginBottom: 20,
      },
      textInput: {
        backgroundColor: '#f0f0f0',
        borderRadius: 5,
        padding: 10,
        marginBottom: 10,
        width: '100%',
        color: 'black',
      },
});
    
const signUpBox = {
  mainBox: {
    backgroundColor: '#fff',
    borderColor: 'black',
    borderWidth: 1,
    borderRadius: 10,
    padding: 20,
  },
  shadowBox: {
    backgroundColor: 'gray',
    borderRadius: 10,
  },
};

const buttonBox = {
  mainBox: {
    backgroundColor: '#fff',
    borderColor: 'black',
    borderWidth: 1,
    borderRadius: 10,
    padding: 10,
    
  },
  shadowBox: {
    backgroundColor: 'gray',
    borderRadius: 10,
  }
}

export default SignUp