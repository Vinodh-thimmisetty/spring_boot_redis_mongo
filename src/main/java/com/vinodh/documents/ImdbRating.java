package com.vinodh.documents;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author thimmv
 * @createdAt 23-06-2019 15:49
 */
@NoArgsConstructor
@Setter
@Getter
public class ImdbRating {
    private Long id;
    private double rating;
    private long votes;
}
