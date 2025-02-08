import React from 'react';
import { useState } from "react"; 

import CardMedia from '@mui/material/CardMedia';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import CardActions from '@mui/material/CardActions';
import Button from '@mui/material/Button';
import CardHeader from '@mui/material/CardHeader';
import { Alert } from '@mui/material';



function Card() {

    const [displayLikeMsg, setDisplayLikeMsg] = useState('none');
    const [displaySubMsg, setDisplaySubMsg] = useState('none');

    function showLikeMsg() {
        setDisplayLikeMsg('block');
    }

    function showSubscribeMsg() {
        setDisplaySubMsg('block')
    }

    return (
        <>
            <CardHeader
                title="Benefits of eating salad"
                subheader="September 14, 2016"
            />
            <CardMedia 
                component="img"
                height="194"
                image="src\assets\eatingSalad.jpg"
                alt="Paella dish"
            />
            <CardContent>
                <Typography>
                    According to the Centers for Disease Control and Prevention, 
                    only 1 in 10 Americans eat the recommended 5 cups of fruits 
                    and vegetables per day. Since the base of a salad is usually 
                    at least 1 to 2 cups of leafy greens, eating a salad every 
                    day can help you meet the daily recommendations. "Eating a 
                    salad helps you better stick to the common suggestion to eat
                     the rainbow," says Brittany DeLaurentis, RD.
                    <br/>
                    If you're worried that salads are boring, DeLaurentis says
                     that doesn't have to be the case: "A common misconception
                      is that salads contain vegetables and nothing else, and
                       eating them will leave you feeling unsatisfied and 
                       hungry." She recommends adding a protein, like cheese, 
                       tofu, beans, chicken or a boiled egg, to increase the 
                       satiety factor.
                </Typography>
            </CardContent>
            <CardActions style={{display: 'flex', flexDirection: 'column'}}>
                <div>
                    <Button size="small" onClick={() => showLikeMsg()}>Like</Button> 
                    <Button size='large' onClick={() => showSubscribeMsg()}>Subscribe</Button>
                </div>
                <div>
                    <div style={{display: displayLikeMsg}}> 
                        <Alert severity='success'> Thanks for the Like! </Alert>
                    </div>
                    <div style={{display: displaySubMsg}}> 
                        <Alert severity='success'> Subscribed </Alert>
                    </div>
                </div>
                
                
            </CardActions>
        </>
    );

}

export default Card;