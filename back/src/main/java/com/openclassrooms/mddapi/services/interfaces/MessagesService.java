package com.openclassrooms.mddapi.services.interfaces;

import com.openclassrooms.mddapi.dto.MessageDto;
import com.openclassrooms.mddapi.entity.Messages;

import java.util.List;

public interface MessagesService {

    void createMessage(Messages messages);

    List<Messages> findAllMessageOfPostId(Long id);

    /**
     * Permet de d'ajouter le nom des auteurs du messages pour la réponse du post détail.
     * @param messages
     */
    void loadUserNames(List<MessageDto> messages);
}
