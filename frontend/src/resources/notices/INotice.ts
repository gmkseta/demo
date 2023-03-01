import {BaseRecord} from "@pankod/refine-core";
import {Field} from "../Field";

export interface INotice extends BaseRecord{
    id: number;
    title: string;
    content: string;

    updatedAt: string;

    createdAt: string;

}

export const noticeFields: Field[] = [
    {
        key: "id",
        type: "number",
    },
    {
        key: "title",
        type: "text",
    },
    {
        key: "content",
        type: "text",
        displayOnly: ["show"]
    },
    {
        key: "updatedAt",
        type: "date",
        displayOnly: ["index", "show"]
    },
    {
        key: "createdAt",
        type: "date",
        displayOnly: ["index", "show"]
    }
]

