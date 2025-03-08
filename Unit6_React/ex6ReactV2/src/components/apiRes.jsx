import { Box, Container, TextField, Typography } from '@mui/material';
import React from 'react';
import { useState, useRef  } from 'react'
import Button from '@mui/material/Button';
import useDogConsultant from '../hooks/dogConsultant';
import DogCard from './dogCard';


function ApiRes({dogbreed}) { 
    let apiRes = useDogConsultant(dogbreed);
    let card = <>Loading</>;

    console.log('apires loading')
    if(apiRes !=null){
        if(apiRes.error == true ){
            card = <p>ERROR!: {apiRes.msg}</p>;
        }
        if(apiRes.error == false ){
            if(apiRes.loading){
                card = <p>loading</p>;
            }
            card = <DogCard data={apiRes.data}></DogCard>;
        }
    }

    return card;
  }

export default ApiRes;