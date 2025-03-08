import { Box, Container, TextField, Typography } from '@mui/material';
import React from 'react';
import { useState, useRef  } from 'react'
import Button from '@mui/material/Button';
import dogConsultant from '../hooks/dogConsultant';


function DogCard({data}) { 
    console.log("dogCard"+ data)
    return (
        <Box component='img' alt="Dog Picture" src={data} sx={{ maxWidth:500, margin: "0 auto"}} />
    );
}

export default DogCard;