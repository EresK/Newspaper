import React, {useEffect, useState} from 'react';
import {Collapse, List, ListItemButton, ListItemText, MenuItem, TextField, Tooltip} from "@mui/material";
import Select from '@mui/material/Select';
import {ExpandLess, ExpandMore} from "@mui/icons-material";
import SendIcon from '@mui/icons-material/Send';
import LoadingButton from '@mui/lab/LoadingButton';
import axios from "axios";
import MemberItem from "./MemberItem";
import "../../styles/Members.css"

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
    const [loading, setLoading] = React.useState(false);

    const [owner, setOwner] = useState(false);
    const [member, setMember] = useState(false);

    useEffect(() => {
        checkOwnerAndMember();
        refreshMembers();
    }, []);

    const checkOwnerAndMember = async () => {
        await axios.get('http://localhost:8080/auth/owner', {
            params: {
                publication_id: props.id_publication,
            },
            headers: {
                Authorization: localStorage.getItem("auth")
            }
        }).then(response => {
            if (response.status === 200 && response.data) {
                setOwner(response.data)
            }
        });

        await axios.get('http://localhost:8080/auth/member', {
            params: {
                publication_id: props.id_publication,
            },
            headers: {
                Authorization: localStorage.getItem("auth")
            }
        }).then(response => {
            if (response.status === 200 && response.data) {
                setMember(response.data)
            }
        });
    }

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
        setLoading(true);

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
            setLoading(false);
            // console.log(response);
            refreshMembers()
        });
    }

    const handleOpen = () => {
        setOpen(!open);
    }

    return (
        <div className='member-div'>
            {owner ? <div className='member-input'>
                    <label className='member-select'>
                        <Select value={role} required={true}
                                onChange={e => setRole(e.target.value)}>
                            {roleOptions.map(option => (
                                <MenuItem value={option.label}>{option.label}</MenuItem>
                            ))}
                        </Select>
                    </label>

                    <label className='member-text'>
                        <TextField variant='outlined' placeholder='Email'
                                   sx={{mt: 0}}
                                   InputProps={{
                                       endAdornment: (
                                           <Tooltip title='Invite'>
                                               <LoadingButton position="end"
                                                              loading={loading}
                                                              onClick={handleSubmit}>
                                                   <SendIcon/>
                                               </LoadingButton>
                                           </Tooltip>
                                       )
                                   }}
                                   onChange={e => setUsername(e.target.value)}/>
                    </label>
                </div> :
                <div></div>
            }

            {member ?
                <div className='member-list'>
                    <List dense='dense'>
                        <ListItemButton onClick={handleOpen}>
                            <ListItemText>Members</ListItemText>
                            {open ? <ExpandLess/> : <ExpandMore/>}
                        </ListItemButton>
                        <Collapse in={open} timeout='auto' unmountOnExit>
                            <List>
                                {members.map(item => (
                                    <MemberItem key={item.id} item={item}
                                                id_publication={props.id_publication}
                                                owner={owner}
                                                refresh={refreshMembers}/>
                                ))}
                            </List>
                        </Collapse>
                    </List>
                    {/*<Button variant='outlined' onClick={refreshMembers}>Refresh</Button>*/}
                </div> :
                <div></div>
            }
        </div>
    );
};

export default Members;