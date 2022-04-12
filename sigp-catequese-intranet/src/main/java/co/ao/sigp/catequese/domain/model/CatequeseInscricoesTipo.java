package co.ao.sigp.catequese.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CatequeseInscricoesTipo {

	LOCAL("Local"),
	ONLINE("Online");

	private final String tipo;
}
