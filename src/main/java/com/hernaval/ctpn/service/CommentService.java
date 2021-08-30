package com.hernaval.ctpn.service;

import com.hernaval.ctpn.service.dto.CommentDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.hernaval.ctpn.domain.Comment}.
 */
public interface CommentService {
    /**
     * Save a comment.
     *
     * @param commentDTO the entity to save.
     * @return the persisted entity.
     */
    CommentDTO save(CommentDTO commentDTO);

    /**
     * Partially updates a comment.
     *
     * @param commentDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CommentDTO> partialUpdate(CommentDTO commentDTO);

    /**
     * Get all the comments.
     *
     * @return the list of entities.
     */
    List<CommentDTO> findAll();

    /**
     * Get the "id" comment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CommentDTO> findOne(Long id);

    /**
     * Delete the "id" comment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
