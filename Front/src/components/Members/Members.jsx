import React, {useEffect, useState} from 'react';
import {Button, Collapse, List, ListItemButton, ListItemText, MenuItem, TextField} from "@mui/material";
import Select from '@mui/material/Select';
import axios from "axios";
import MemberItem from "./MemberItem";
import {ExpandLess, ExpandMore} from "@mui/icons-material";

const Members = (props) => {
    const [username, setUsername] = useState('');
    const roleOptions = [
        {value: 'Choose role', label: 'Choose role'},
        {value: 'editor', label: 'Editor'},
        {value: 'advertiser', label: 'Advertiser'},
        {value: 'designer', label: 'Designer'}
    ];
    const [role, setRole] = useState(roleOptions[0].value);
    const [members, setMembers] = useState([]);
    const [open, setOpen] = React.useState(false);

    useEffect(() => {
        refreshMembers();
    }, [members]);

    const refreshMembers = async () => {
        await axios.get('http://localhost:8080/permissions', {
            params: {
                id: props.id_publication,
            },
            headers: {
                Authorization: localStorage.getItem("auth")
            }
        }).then(response => {
            // console.log(response.data)
            if (response.status === 200 && response.data) {
                setMembers(response.data)
            }
        });
    }

    const handleSubmit = async () => {
        await axios.post('http://localhost:8080/permissions', {}, {
            params: {
                user_email: username,
                publication_id: props.id_publication,
                role: role
            },
            headers: {
                Authorization: localStorage.getItem("auth")
            }
        }).then(response => {
            // console.log(response);
            refreshMembers()
        });
    }

    const handleOpen = () => {
        setOpen(!open);
    }

    return (
        <div>
            <div>
                <label>
                    <TextField variant='outlined' placeholder='Email'
                               onChange={e => setUsername(e.target.value)}/>
                </label>
                <label>
                    <Select value={role} required={true}
                            onChange={e => setRole(e.target.value)}>
                        {roleOptions.map(option => (
                            <MenuItem value={option.label}>{option.label}</MenuItem>
                        ))}
                    </Select>
                </label>
                <Button variant='outlined' onClick={handleSubmit}>Invite</Button>
            </div>

            <div>
                <List dense='dense'>
                    <ListItemButton onClick={handleOpen}>
                        <ListItemText>Members</ListItemText>
                        {open ? <ExpandLess/> : <ExpandMore/>}
                    </ListItemButton>
                    <Collapse in={open} timeout='auto' unmountOnExit>
                        <List>
                            {members.map(item => (
                                <MemberItem key={item.id} item={item}
                                            id_publication={props.id_publication} refresh={refreshMembers}/>
                            ))}
                        </List>
                    </Collapse>
                </List>
                {/*<Button variant='outlined' onClick={refreshMembers}>Refresh</Button>*/}
            </div>
        </div>
    );
};

export default Members;