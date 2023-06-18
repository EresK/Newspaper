import React from 'react';
import {Avatar, IconButton, ListItem, ListItemAvatar, ListItemText} from "@mui/material";
import DeleteIcon from '@mui/icons-material/Delete';
import axios from "axios";
import PersonIcon from '@mui/icons-material/Person';

const MemberItem = (props) => {
    const username = props.item.user.email;
    const id_publication = props.id_publication;
    const role = props.item.role;

    const handleDeleteMember = async (e) => {
        e.preventDefault();

        await axios.delete('http://localhost:8080/permissions', {
            params: {
                user_email: username,
                publication_id: id_publication,
                role: role
            },
            headers: {
                Authorization: localStorage.getItem("auth")
            }
        }).then(response => {
            // console.log(response);
            props.refresh(e);
        });
    }

    return (
        <div>
            <ListItem key={props.key} secondaryAction={
                <IconButton edge='end' aria-label='delete' onClick={handleDeleteMember}>
                    <DeleteIcon/>
                </IconButton>
            }>
                <ListItemAvatar>
                    <Avatar>
                        <PersonIcon/>
                    </Avatar>
                </ListItemAvatar>
                <ListItemText>{username}: {role}</ListItemText>
            </ListItem>
        </div>
    );
};

export default MemberItem;