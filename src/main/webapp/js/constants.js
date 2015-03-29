'use strict';

angular.module('constants', [])

.constant('StatutCommandeEcole', {
  'EN_COURS': 'EN_COURS',
  'COMMANDEE': 'COMMANDEE',
  'EN_LIVRAISON': 'EN_LIVRAISON',
  'LIVREE': 'LIVREE',
  'ABANDONNEE': 'ABANDONNEE'}
)
.constant('StatutCommandeFamille', {
  'EN_COURS': 'EN_COURS',
  'EN_ATTENTE_VALID_RESPONSABLE': 'EN_ATTENTE_VALID_RESPONSABLE',
  'EN_LIVRAISON': 'EN_LIVRAISON',
  'LIVREE': 'LIVREE',
  'ABANDONNEE': 'ABANDONNEE'}
)
.constant('StatutPaiementCommandeFamille', {
	  'PAYE': 'PAYE',
	  'NON_PAYE': 'NON_PAYE'}
);